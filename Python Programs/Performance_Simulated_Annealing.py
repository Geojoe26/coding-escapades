# -*- coding: utf-8 -*-
"""
Created on Thu Nov 24 14:41:47 2022

@author: Joel George
"""
import time
import random
import math
import numpy as np
import matplotlib.pyplot as plt

def function(X): #Defining the objective function as defined in Q3
    x1=X[0]
    x2=X[1]
    value = -x1*math.sin(np.sign(x1)*(np.abs(x1)**0.5))-x2*math.sin(np.sign(x2)*(np.abs(x2)**0.5))
    return value

total_var = 2 #Number of variables in the objective function

#Since xi < 1000 in the problem statement, setting higher & lower limits for the two variables
higher_limit = [999,999]  
lower_limit = [-999,-999]  

#Initial temperature set for initializing the method
ignition_init_temp = 100

#Rate at which the temperature needs to reduced, we'll term it  as cooling co-efficient
cooling_coefficient = 0.05

starting_set_pts=np.zeros((total_var)) #For initializing, initially we select zero, zero as the two variables

#Then randomly selecting initial pts, setting two variables using a for loop to iteratively set random variables
for var in range(total_var):
    starting_set_pts[var] = (random.uniform(lower_limit[var],higher_limit[var]))

#Initializing, variables to set current set of pts & ideal solution pts which would be used during the course of the method
#Temporarily, they are initialized using the starting pts which are subject to change as the program proceeds later on.
current_set_pts = starting_set_pts
ideal_set_pts = starting_set_pts

n = 1  # no of solutions we are expecting to accept

ideal_fitness = function(ideal_set_pts) #Evalated result of the pts selected in objective function
current_temperature = ignition_init_temp # current temperature

start = time.time() #start parameter initialized to calculate time taken
total_attempts = 75 # number of cycles in each level of temperature
record_best_fitness =[] #List of accepted solutions

for i in range(99999): #No.of Iterations
    #Var j is to loop through the number of attempts set.
    for j in range(total_attempts):
        #Finally loop k to set, next solution pts
        for k in range(total_var):
            current_set_pts[k] = (ideal_set_pts[k]) + (0.01)*((random.uniform(lower_limit[k],higher_limit[k]))) #0.01 is the acceptance function
            current_set_pts[k]=(max(min(current_set_pts[k],higher_limit[k]),lower_limit[k]))
        current_eval_fitness=function(current_set_pts)  #Re-calculate the cost function with new pts received
        E=(abs(current_eval_fitness-ideal_fitness)) #Calculating the change in energy
        
        #In case of initial run
        if i==0 and j==0:
            EA=E #Setting the var EA, to initial change of energy 'E', similar to boltzmann constant
        
        #Logic to accept solutions
        if current_eval_fitness > ideal_fitness: #Incase of finding Minima
            p=math.exp(-E/(EA*current_temperature))#Calculating the probability function.
            
            if random.random()<p:
                accept=True#accept the solution
            else:
                accept=False#don't accept the solution
        else:
            accept=True#accept solution

        if accept==True: #Once a solution is accepted
            ideal_set_pts=current_set_pts  #update with the current solution
            ideal_fitness=function(ideal_set_pts)
            
            n=n+1#Total count of solutions
            
            EA=(EA*(n-1)+E)/n #updating the value of EA, upon accepting a solution       
        
    record_best_fitness.append(ideal_fitness) #creating a list of accepted points
    #cooling the temp
    current_temperature=current_temperature*cooling_coefficient
    
    #Printing the results
    print('Ideal_Solution:{}\nCorresponding_Cost_function_Value:{}\n'.format(ideal_set_pts,ideal_fitness))
       
    #stop by computing time
    end=time.time()
    
    #Just to calculate the time taken
    evaluation_time = end-start
    
    #To indicate the completion of the algorithm execution
    if current_temperature == 0.00:
        break
    
#Plotting the optimization Trajectory
plt.plot(record_best_fitness, color='red')
plt.title("Trajectory of Optimization", fontweight = 'bold')
plt.xlabel('Iterations ------->', fontweight = 'bold')
plt.ylabel('Cost Function Values ------->', fontweight = 'bold')
plt.grid(True)

#To calculate Mean
total = 0

for ele in range(0, len(record_best_fitness)):
    total = total + record_best_fitness[ele]

#To calculate variance
m=0
m = sum(record_best_fitness) / len(record_best_fitness)

var_res = sum((item - m) ** 2 for item in record_best_fitness) / len(record_best_fitness)
 

#Performance Stats
print('##################### PERFORMANCE STATS ############################')
print('Total Iterations :', i+1)
print('Total time taken :', evaluation_time , 'sec(s)')
print('Final Temperature :', current_temperature)

# printing total value
print("Sum of all cost functions: ", total)
print("Mean of the method :", m)
print("Variance of the method :", var_res)
print("Standard Deviation of the method :", np.std(record_best_fitness)) #Std deviation calculated by function of NumPy
print('###################################################################')
