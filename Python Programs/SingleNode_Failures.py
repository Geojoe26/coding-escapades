import matplotlib.pyplot as plt
import networkx as nx
import numpy as np
import random as rm
from collections import Counter

####################################### INITIAL SETUP ############################################

netsc_data = nx.read_edgelist('netscience.mtx')

#Finding Number of nodes
no_nodes = netsc_data.nodes() 

#Forming a list of degrees for all the nodes
degree_list = dict(netsc_data.degree())

#Setting the number of simulations
simulations = 75

#Storing the resultant node failure values
node_failure_freq = []
##################################################################################################

#Simulating the node failures
for simulation in range(simulations):
    flag = False #initializing the simulation

    #Setting the initial state of all the nodes as functional node
    init_state = {node: 0 for node in no_nodes}
    
    #Selecting a single node at random to fail as a part of failure propagation
    index = rm.choice(list(no_nodes))
    init_state[index] = 1
    
    #Updating the state after random failure of a single node
    upd_state = init_state.copy()

    #Looping through to find the neighbours of the nodes
    while not flag:
        flag = True
        
        #Checking every single node in the network for their failed neighbours
        for node in no_nodes:
            failed_neigh = [j for j in netsc_data.neighbors(node) if init_state[j] == 1]            
            k = degree_list[node] #retrieving the degree of the node
            kF = len(failed_neigh) #finding the failed neighbours
            
            #Setting the probability as mentioned in the question
            probability = kF/k
            
            #Updating the state of the node to failed upon the below condition
            if (rm.random() >= probability) and init_state[node] == 0:
                upd_state[node] = 1
                flag = False

        #updating the state of the network after simulation completion
        init_state = upd_state.copy()    
    
    #Updating the total number of nodes failed in a simulation after every simulation
    node_failure_freq = node_failure_freq+[sum(list(init_state.values()))]

################################### PLOTTING THE RESULTS #########################################
plt.hist(node_failure_freq, bins=50, color='red', edgecolor='black') 
plt.xlabel('Failing number of nodes in every simulation ----->')
plt.ylabel('Cumulative Frequency ----->')
plt.title('Plot Distribution of Avalanche size for Single node Failures')
plt.grid()
plt.show()
##################################################################################################

#Printing the distribution
distribution = Counter(node_failure_freq)
for key,value in distribution.most_common():
    print(key,value)