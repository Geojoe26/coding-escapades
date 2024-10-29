# -*- coding: utf-8 -*-
"""
Created on Sat Dec  3 16:16:31 2022

@author: Joel George
"""

import time
import random
import math
import numpy as np
import matplotlib.pyplot as plt
#------------------------------------------------------------------------------
# Customization section:

initial_temperature = 100
cooling = 0.25  # cooling coefficient
number_variables = 2
upper_bounds = [999,999]  
lower_bounds = [-999,-999]  
computing_time = 100 # second(s)
  
def objective_function(X):
    x1=X[0]
    x2=X[1]
    value = -x1*math.sin(np.sign(x1)*(np.abs(x1)**0.5))-x2*math.sin(np.sign(x2)*(np.abs(x2)**0.5))
    return value
  
#------------------------------------------------------------------------------
# Simulated Annealing Algorithm:
initial_solution=np.zeros((number_variables))
for v in range(number_variables):
    initial_solution[v] = (random.uniform(lower_bounds[v],upper_bounds[v]))
      
current_solution = initial_solution
best_solution = initial_solution
n = 1  # no of solutions accepted
best_fitness = objective_function(best_solution)
current_temperature = initial_temperature # current temperature
start = time.time()
no_attempts = 25 # number of attempts in each level of temperature
record_best_fitness =[]
  
for i in range(7500):
    for j in range(no_attempts):
        for k in range(number_variables):
            current_solution[k] = (best_solution[k]) + (0.01)*((random.uniform(lower_bounds[k],upper_bounds[k])))
            current_solution[k]=(max(min(current_solution[k],upper_bounds[k]),lower_bounds[k]))
        current_fitness=objective_function(current_solution)
        E=(abs(current_fitness-best_fitness))
        if i==0 and j==0:
            EA=E
            
        if current_fitness > best_fitness:
            p=math.exp(-E/(EA*current_temperature))#make a decision to accept the worst solution.
            if random.random()<p:
                accept=True#accept the worst sol
            else:
                accept=False#dosent accept the worst sol
        else:
            accept=True#accept better sol
        if accept==True:
            best_solution=current_solution#update the best sol
            best_fitness=objective_function(best_solution)
            n=n+1#count the solutions accepted
            EA=(EA*(n-1)+E)/n #update EA
    print('iteration:{},current_Temp:{},best_solution:{},best_fitness:{}\n'.format(i,current_temperature,best_solution,best_fitness))
    record_best_fitness.append(best_fitness)
    #cooling the temp
    current_temperature=current_temperature*cooling
    #stop by computing time
    end=time.time()
    if end-start>=computing_time:
        break
plt.plot(record_best_fitness, color='red')
plt.title("Trajectory of Optimization", fontweight = 'bold')
plt.xlabel('Iterations ------->', fontweight = 'bold')
plt.ylabel('Cost Function Values ------->', fontweight = 'bold')
plt.grid(True)