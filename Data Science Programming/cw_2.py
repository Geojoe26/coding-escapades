# -*- coding: utf-8 -*-
"""
Created on Fri Jan 13 22:03:04 2023

@author: Joel George
"""
############################################ 2A ##################################################

import numpy as np
import pandas as pd
import glob as gb

#Reading and storing all the filenames in a list
files = gb.glob("Schools_*.csv")

#Created a boolean variable to identify the first file
first_file_ind = False

#looping through individual files in the list to read and create a dataframe 'school_info'
for file in files:
    current_school = pd.read_csv(file);
    
    #Logic to read files and concat to a single dataframe
    if first_file_ind == False:
        school_info = current_school
        first_file_ind = True #Since first file has been read setting it to True
    else:
        school_info = pd.concat([school_info, current_school], axis=1)

#Printing the dataframe
print(school_info)
rows, cols = school_info.shape
print('The dataframe contains {} rows &  {} columns'.format(rows, cols))

#Transpose the dataframe to perform removal operations with ease
school_info = school_info.T

#Removing Schools that have closed down using column index 11
school_info.drop(school_info[school_info[11] == 'Closed'].index, inplace = True)

#Removing Schools that dont provide secondary school & post 16 education using column index 17 & 18
school_info.drop(school_info[school_info[17] == '0'].index, inplace = True)
school_info.drop(school_info[school_info[18] == '0'].index, inplace = True)

#Removing schools having no values in 'Ofsted' & 'Best 3 A level entries' which are column index 24 & 1928
school_info.dropna(axis=0, subset=24, inplace= True)
school_info.dropna(axis=0, subset=1928, inplace= True)

#Reverting the transpose to have the original shape of the dataframe after operations
school_info = school_info.T

#Printing the dataframe
print(school_info)
rows, cols = school_info.shape
print('The dataframe contains {} rows &  {} columns'.format(rows, cols))
############################################ 2B ##################################################

#Removing the rows specified in the question, values used are specific to their indices in the dataframe
school_row_no = [10,24,47,79,357,767,768,769,1480,1928,1931]
school_info = school_info.loc[school_row_no]

#Printing the dataframe after the operation
print(school_info)

rows, cols = school_info.shape
print('The dataframe contains {} rows &  {} columns'.format(rows, cols))
#Performing a transpose as directed
school_info = school_info.T

# Column names after transpose operation
print('Column names before renaming:',school_info.columns)

#Renaming the column names for the dataframe and printing them
school_info.set_axis(['postcode', 'Ofsted', 'FSM', 'PT_ratio', 'absentees', 'top_third', 'Russell', 'Oxbridge', 'GCSE', 'best_3Alevels', 'ratio_AAB'], axis=1, inplace=True)
print('Column names after renaming:',school_info.columns)

#First three row names for each file are identified and put in a list
#Using the list specific rows have been dropped.
row_list = ['No', 'Variable', 'Namespace']
school_info.drop(row_list, axis=0, inplace=True)
rows, cols = school_info.shape
print('The dataframe contains {} rows &  {} columns'.format(rows, cols))

############################################ 2C ##################################################
#Replacing values
school_info['Ofsted'] = school_info['Ofsted'].replace(['Outstanding','Good','Requires improvement','Inadequate'],[4,3,2,1])

#Removing '%' from the entire dataframe
school_info = school_info.replace({'%':''}, regex=True)

#Removing rows with null values in columns 'PT_ratio' and 'top_third'
school_info.dropna(axis=0, subset=['PT_ratio','top_third'], inplace= True)

#Removing rows with values 'NE' and 'SUPP' from column 'ratio_AAB'
school_info.drop(school_info[(school_info.ratio_AAB == 'NE') | (school_info.ratio_AAB == 'SUPP')].index, inplace = True)

#Printing row and column count after these operations
rows, cols = school_info.shape
print('The dataframe contains {} rows &  {} columns'.format(rows, cols))
print(school_info)

#Removing rows from column 'postcode' which do not start with 'B'
school_info = school_info.loc[school_info['postcode'].str.startswith('B')].copy()

#Creating a new column 'PC' copying column 'postcode'
school_info['PC'] = school_info['postcode']
school_info['PC'] = school_info['PC'].str.replace(r'[A-Z]','', regex=True)
school_info['PC'] = school_info['PC'].str.replace(r'[\s]','', regex=True)
school_info['PC'] = school_info['PC'].astype(int)

#Printing rows and columns after operations
rows, cols = school_info.shape
print('The dataframe contains {} rows &  {} columns'.format(rows, cols))
print(school_info)
############################################ 2D ##################################################
#Creating a new dataframe 'Full' excluding 'postcode' column
Full = school_info.drop('postcode', axis=1).astype('float')

#Grouping records to get mean for batches of individual PC
Full = Full.groupby(by='PC').mean()

#Printing rows and columns after operations
rows, cols = Full.shape
print('The dataframe contains {} rows &  {} columns'.format(rows, cols))
print(Full)
############################################ 2E ##################################################

#Pairwise correlation of the dataframe
print(Full.corr())

############################################ 2F ##################################################
#Getting task 1 extract
task_one_df = pd.read_csv('extract.csv')

#Making a list of 'PC' values from 'Full' dataframe, 
#so that only those values can be taken from Task 1 'prop_data_pc_norm' dataframe, for ease of merging.
required_PC = Full.index.astype(int)
task_one_df = task_one_df[task_one_df['PC'].isin(required_PC)]

#Merging the dataframes on column 'PC' which is common to both
combined_full_prop_data_df = pd.merge(Full, task_one_df, on ='PC')
print(combined_full_prop_data_df)

#Creating a csv file to read this file in R for further evaluation
combined_full_prop_data_df.to_csv('2f_data.csv',index=False)

#Importing sklearn libraries
import sklearn.metrics as metrics
from sklearn.preprocessing import normalize
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split

#Normalizing data before regression using sklearn
norm_combined_full_prop_data_df = normalize(combined_full_prop_data_df)

#Creating training data segregating the data to features and price set
features_set = norm_combined_full_prop_data_df[:,0:10]
price_set = norm_combined_full_prop_data_df[:,11]

#Splitting the data train & test in the ratio of 80:20 respectively.
comb_df_ft_train, comb_df_ft_test, comb_df_pr_train, comb_df_pr_test = train_test_split(features_set, price_set, test_size = 0.20)

#Using sklearn class linear regression to regress the data
comb_regress = LinearRegression()
comb_regress.fit(comb_df_ft_train, comb_df_pr_train)

#Storing prediction of price data to compare later on
pred_price = comb_regress.predict(comb_df_ft_test)

#Printing the values to compare
print("Prediction Price Values : {}".format(pred_price))
print("Test Price Values : {}".format(comb_df_pr_test))

#Testing error metrics
error_val = metrics.mean_squared_error(comb_df_pr_test, pred_price)
print("Training error : {}".format(error_val))