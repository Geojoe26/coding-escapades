# -*- coding: utf-8 -*-
"""
Created on Tue Jan  3 16:14:15 2023

@author: Joel George
"""
############################################ 1A ##################################################

import numpy as np
import pandas as pd
import re
    
    
#The File Prices.csv is placed in the same directory as that of the jupyter file to run
prop_prices_data = pd.read_csv('Prices.csv')
rows, cols = prop_prices_data.shape
print('The dataframe contains {} rows &  {} columns'.format(rows, cols))

#Printing column names from the dataframe before removal of columns
print('Dataframe column names :',prop_prices_data.columns.values)

#Removing columns unique id, saon, paon, street, locality, district and county from the dataframe.
prop_prices_data.drop(columns=['unique_id','saon', 'paon', 'street', 'locality',
'district', 'county'], inplace=True)
print('Dataframe column names after removal:',prop_prices_data.columns.values)

#To remove records from dataframe having 'property_type' value as 'O'
prop_prices_data = prop_prices_data[prop_prices_data['property_type'].values != 'O']
rows, cols = prop_prices_data.shape
print('The dataframe contains {} rows &  {} columns'.format(rows, cols))


#Replacing values
prop_prices_data['new_build'] = prop_prices_data['new_build'].replace(['Y','N'],[1,0])
prop_prices_data['estate_type'] = prop_prices_data['estate_type'].replace(['F','L'],[1,0])
prop_prices_data['transaction_category'] = prop_prices_data['transaction_category'].replace(['A','B'],[1,0])

#Removing empty post codes from column 'postcode'
prop_prices_data.dropna(axis=0, subset='postcode', inplace= True)
rows, cols = prop_prices_data.shape
print('The dataframe contains {} rows &  {} columns'.format(rows, cols))

#Creating a new column 'PC' copying column 'postcode'
prop_prices_data['PC'] = prop_prices_data['postcode']
prop_prices_data['PC'] = prop_prices_data['PC'].str.replace(r'[A-Z]','', regex=True)
prop_prices_data['PC'] = prop_prices_data['PC'].str.replace(r'[\s]','', regex=True)
prop_prices_data['PC'] = prop_prices_data['PC'].astype(int)

rows, cols = prop_prices_data.shape
print('The dataframe contains {} rows &  {} columns'.format(rows, cols))

print(prop_prices_data)
############################################ 1B ##################################################

#Importing the package datetime and creating a function to count the number of days
from datetime import datetime
def day_count_fn(deed_dt):
    dt_frmt = '%d/%m/%Y'

    #Created an empty data dictionary to fill in day counts, count var initiatlized to be used as a key.
    day_val_arr = {}
    count = 0
    
    #Looping through each day value in 'deed_date' column to calculate count
    for day in deed_dt:
        fn_deed_dt = datetime.strptime(day, dt_frmt)
        st_deed_dt = datetime.strptime('01/01/1995', dt_frmt)
        
        day_cnt = fn_deed_dt - st_deed_dt
     
        day_val_arr[count] = day_cnt.days+1 #Storing the values
        count=count+1 #Incrementing at each iteration
        
    return day_val_arr.values() #Returning only the values

#Called the function to create a new column 'days' passing the values in 'deed_date' column
prop_prices_data['days'] = day_count_fn(prop_prices_data['deed_date'])

rows, cols = prop_prices_data.shape
print('The dataframe contains {} rows &  {} columns'.format(rows, cols))
print(prop_prices_data)
############################################ 1C ##################################################

#Property Type plot for sales of different types
prop_type_unique_values_counts = prop_prices_data.property_type.value_counts()
import matplotlib.pyplot as plt

prop_type_unique_values_counts.plot(kind = 'bar',
          y = 'property_type',
          x = 'Index',
          color ='red',
          edgecolor ='black')

plt.title('Property Type Vs Sales')
plt.xlabel('Property Type ----->')
plt.ylabel('Sales ----->')
plt.grid(visible=True , color='red')
plt.show()

#Plot of Sales Vs New_build
new_build_unique_values_counts = prop_prices_data.new_build.value_counts()
new_build_unique_values_counts.plot(kind = 'bar',
          y = 'new_build',
          x = 'Index',
          color ='green',
          edgecolor ='black')

plt.title('New Build Vs Sales')
plt.xlabel('New Build ----->')
plt.ylabel('Sales ----->')
plt.grid(visible=True , color='green')
plt.show()

#Plot of Sales Vs Estate Type
estate_type_unique_values_counts = prop_prices_data.estate_type.value_counts()
estate_type_unique_values_counts.plot(kind = 'bar',
          y = 'estate_type',
          x = 'Index',
          color ='magenta',
          edgecolor ='black')

plt.title('Estate Type Vs Sales')
plt.xlabel('Estate Type ----->')
plt.ylabel('Sales ----->')
plt.grid(visible=True , color='magenta')
plt.show()

#Plot of Sales Vs transaction_type
trnx_type_unique_values_counts = prop_prices_data.transaction_category.value_counts()
trnx_type_unique_values_counts.plot(kind = 'bar',
          y = 'transaction_category',
          x = 'Index',
          color ='orange',
          edgecolor ='black')

plt.title('Transaction Category Vs Sales')
plt.xlabel('Transaction Category ----->')
plt.ylabel('Sales ----->')
plt.grid(visible=True , color='orange')

plt.show()

############################################ 1D ##################################################

#Dataframe should only have records with detached house properties i.e prop_prices_data['property_type'] = D
prop_prices_data = prop_prices_data[prop_prices_data['property_type'].values == 'D']
rows, cols = prop_prices_data.shape
print('The dataframe contains {} rows &  {} columns'.format(rows, cols))

#Grouping records to get a count for batches of 365 days
prop_prices_data_sales = prop_prices_data.groupby(pd.cut(prop_prices_data['days'], np.arange(0,26*365,365))).count()
#Adding column 'days in a yr to represent each row as a year of 365 days taking Day 183 as midpoint
prop_prices_data_sales['days_in_yr'] = ((np.arange(len(prop_prices_data_sales)))*365)+183

#evaluating the line of best fit using NumPy polyfit method and added a column to populate respective values
m,c = np.polyfit(prop_prices_data_sales['days_in_yr'], prop_prices_data_sales['property_type'], 1)
line = m*prop_prices_data_sales['days_in_yr']+c
prop_prices_data_sales['line_fitting'] = line

#Plotting the scatter graph for sales vs days
ax = prop_prices_data_sales.plot(kind = 'scatter',
          y = 'property_type',
          x = 'days_in_yr',
          color ='black',
          label = 'values')
prop_prices_data_sales.set_index('days_in_yr', inplace=True)
prop_prices_data_sales.line_fitting.plot(ax=ax, color ='green')
plt.gca()
plt.title('Detached House Sales Vs Days')
plt.ylabel('Detached House Sales ----->')
plt.xlabel('Days ----->')
plt.grid()
plt.show()
############################################ 1E ##################################################

#Grouping records to get mean for batches of 365 days
prop_prices_data_price_mean = prop_prices_data.groupby(pd.cut(prop_prices_data['days'], np.arange(0,26*365,365))).mean()
#Adding column 'days in a yr' to represent each row as a year of 365 days taking Day 183 as midpoint
prop_prices_data_price_mean['days_in_yr'] = ((np.arange(len(prop_prices_data_sales)))*365)+183

#evaluating the line of best fit using NumPy polyfit method and added a column to populate respective values
m,c = np.polyfit(prop_prices_data_price_mean['days_in_yr'], prop_prices_data_price_mean['price_paid'], 1)
line = m*prop_prices_data_price_mean['days_in_yr']+c
prop_prices_data_price_mean['line_fitting'] = line

#Plotting the scatter graph for sales vs days
ax = prop_prices_data_price_mean.plot(kind = 'scatter',
          y = 'price_paid',
          x = 'days_in_yr',
          color ='purple',
          label = 'values')
prop_prices_data_price_mean.set_index('days_in_yr', inplace=True)
prop_prices_data_price_mean.line_fitting.plot(ax=ax, color ='pink')
plt.gca()
plt.title('Detached House Price Mean Vs Days')
plt.ylabel('Detached House Mean Prices ----->')
plt.xlabel('Days ----->')
plt.grid()
plt.show()

#Created a new column 'normalised_price' and normalized the values in 'price_paid' column using the line of best fitting values 
prop_prices_data['normalised_price'] = (prop_prices_data['price_paid'] - np.min(prop_prices_data_price_mean['line_fitting']))/(np.max(prop_prices_data_price_mean['line_fitting']) - np.min(prop_prices_data_price_mean['line_fitting']))

#Printing updated dataset rows & column count
rows, cols = prop_prices_data.shape
print('The dataframe contains {} rows &  {} columns'.format(rows, cols))

############################################ 1F ##################################################

#Creating new dataframe having only columns 'PC' and 'normalised_price' from main dataframe 'prop_prices_data'
prop_data_pc_norm = prop_prices_data.filter(['PC','normalised_price'],axis=1)
print(prop_data_pc_norm)

#Grouping record by each 'PC' and calculating the mean
prop_data_pc_norm = prop_data_pc_norm.groupby(by='PC').mean()
print(prop_data_pc_norm)

#Printing the 10 largest normalized values & PC values
print(prop_data_pc_norm.nlargest(10,'normalised_price'))

prop_data_pc_norm.to_csv('extract.csv')
############################################ 1G ##################################################
#Importing sklearn libraries
import sklearn.metrics as metrics
from sklearn.preprocessing import normalize
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split

#Dropping unwanted columns to have only columns used in regression model
prop_prices_data.drop(columns=['deed_date', 'postcode', 'property_type', 'normalised_price'], inplace=True)

#Normalizing data before regression using sklearn
prop_norm_data = normalize(prop_prices_data)

#Creating training data segregating the data to features and price set
features_set = prop_norm_data[:,1:6]
price_set = prop_norm_data[:,0]

#Splitting the data train & test in the ratio of 80:20 respectively.
features_set_train, features_set_test, price_set_train, price_set_test = train_test_split(features_set, price_set, test_size = 0.20)

#Using sklearn class linear regression to regress the data
regress = LinearRegression()
regress.fit(features_set_train, price_set_train)

#Storing prediction of price data to compare later on
pred_price = regress.predict(features_set_test)

#Printing the first 5 values to compare
print("Prediction Price Values : {}".format(pred_price[:5]))
print("Test Price Values : {}".format(price_set_test[:5]))

#Testing error metrics
error_val = metrics.mean_squared_error(price_set_test, pred_price)
print("Training error : {}".format(error_val))

############################################ 1H ##################################################

import torch

#Normalizing data using Min Max normalization
normal_prop_data = pd.DataFrame()
normal_prop_data = ((prop_prices_data - np.min(prop_prices_data))/(np.max(prop_prices_data) - np.min(prop_prices_data))).astype(float)
print(normal_prop_data)

#Creating training data segregating the data to features and price set
features_set = normal_prop_data.drop('price_paid', axis=1)
price_set = normal_prop_data['price_paid']

#Converting them to tensors
t_features_set = torch.Tensor(features_set.values)
t_price_set = torch.Tensor(price_set.values)

#Splitting the data train & test in the ratio of 80:20 respectively.
t_features_set_train, t_features_set_test, t_price_set_train, t_price_set_test = train_test_split(t_features_set, t_price_set, test_size = 0.20)

# Defining trainable parameters : weights and bias 
weight_mx = torch.randn((5, 1), requires_grad=True)
bias_mx = torch.randn(1, requires_grad=True)

# Then we define the prediction model
def linear_reg_model(x_input):
    return x_input.mm(weight_mx) + bias_mx

#Defining the loss criterion
loss_criterion = torch.nn.MSELoss()
 
#Using Adam stochastic method optimizer with params and learning rate (lr)
optimizer_meth = torch.optim.Adam([weight_mx,bias_mx], lr= 0.01)

for epoch in range(20):

 	# Forward pass: predicting paid_price using features set
 	prediction_price = linear_reg_model(t_features_set_train)

 	# Evaluating loss
 	error_loss = loss_criterion(prediction_price, t_price_set_train)

 	# Considering zero gradients, performing a backward pass and updating the weights.
 	optimizer_meth.zero_grad()
 	error_loss.backward()
 	optimizer_meth.step()
 	print('epoch {}, loss {}'.format(epoch, error_loss.item()))

############################################ 1I ##################################################
# pytorch mlp for regression
from numpy import vstack
from numpy import sqrt
from sklearn.metrics import mean_squared_error
from torch.utils.data import Dataset
from torch.utils.data import DataLoader
from torch.utils.data import random_split
from torch.nn import Linear
from torch.nn import Sigmoid
from torch.nn import Module
from torch.optim import SGD
from torch.nn import MSELoss
from torch.nn.init import xavier_uniform_

# dataset definition
class norm_data(Dataset):
    # load the dataset
    def __init__(self, feed):
        dat_f = feed.astype('float32')
        # separating input features and price_paid 
        self.feat_set = dat_f.values[:, :-1].astype('float32')
        self.pr_set = dat_f.values[:, -1].astype('float32')
        # Reshaping
        self.pr_set = self.pr_set.reshape((len(self.pr_set), 1))

    # length of feed
    def __len__(self):
        return len(self.feat_set)

    # Location rows at an index
    def __getitem__(self, index):
         return [self.feat_set[index], self.pr_set[index]]

    # Splitting data as 80:20
    def segregate(self, n_test=0.20):
        test_size = round(n_test * len(self.feat_set))
        train_size = len(self.feat_set) - test_size
        return random_split(self, [train_size, test_size])

# Defining the model
class Perceptron(Module):
    # define model elements
    def __init__(self, feat_imputs):
        super(Perceptron, self).__init__()
        # input to first hidden layer
        self.first_layer = Linear(feat_imputs, 10)
        xavier_uniform_(self.first_layer.weight)
        self.activatn = Sigmoid()
        self.final_layer = Linear(10, 1)
        xavier_uniform_(self.final_layer.weight)

    # forward propagate input
    def forward(self, feat_set):
        # input to first hidden layer
        feat_set = self.first_layer(feat_set)
        feat_set = self.activatn(feat_set)
        # output
        feat_set = self.final_layer(feat_set)
        return feat_set

# procesing the dataset
def data_prep(feed):
    # feeding the dataset
    obj = norm_data(feed)
    # separate data as test and train data
    train, test = obj.segregate()
    # Data loaders
    train = DataLoader(train, batch_size=2000, shuffle=True)
    test = DataLoader(test, batch_size=4000, shuffle=False)
    return train, test

# Training the model
def train_model(feed, model):
    # define the optimization
    criterion = MSELoss()
    optimizer = SGD(model.parameters(), lr=0.01)
    # looping to train the model
    for epoch in range(50):
        # enumerate mini batches

        for i, (features, pr) in enumerate(feed):
            
            # Considering 0 gradients
            optimizer.zero_grad()
            # estimate the mresult
            ev_value = model(features)
            # loss valuation
            loss = criterion(ev_value, pr)
            # feed the error via backpropagation
            loss.backward()
            # update weights
            optimizer.step()

# test function for the model
def evaluate_model(feed, model):
    test_value, actual_value = list(), list()
    for i, (features, pr) in enumerate(feed):
        # Testing the model on test set data
        test_ev_value = model(features)
        # retrieve numpy array
        test_ev_value = test_ev_value.detach().numpy()
        origin_dt = pr.numpy()
        origin_dt = origin_dt.reshape((len(test_ev_value), 1))
        # store
        test_value.append(test_ev_value)
        actual_value.append(origin_dt)
    test_value, actual_value = vstack(test_value), vstack(actual_value)
    # calculate mse
    error = mean_squared_error(actual_value, test_value)
    return error

# Data preparation feeding the normalized data to the system
train_data, test_data = data_prep(normal_prop_data)
# Network Definition with number of features as input
neural_model = Perceptron(5)
# Training the network
train_model(train_data, neural_model)
# Testing the network
testing_err = evaluate_model(test_data, neural_model)
print('Mean Sqrt Error: %.5f, Root of the error: %.5f' % (testing_err, sqrt(testing_err)))