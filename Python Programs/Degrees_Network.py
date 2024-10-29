# -*- coding: utf-8 -*-
"""
Created on Thu Mar 16 00:26:17 2023

@author: Joel George
"""
import networkx as nx
import matplotlib.pyplot as plt
import numpy as np
from scipy import stats as st
from scipy.special import binom
from random import sample

nodes = 200         #N
prob_rewire = 0.7   #p
k = 10              #nearest_neighbour
rand_seed = 367   #generating the random seed

#generating_graph
gr_2C = nx.watts_strogatz_graph(nodes, k, prob_rewire, rand_seed)
gr_2C = nx.DiGraph(gr_2C)

#Generating degrees of each node as a list
in_degree = [gr_2C.in_degree(n) for n in gr_2C.nodes()] #In-degree
out_degree = [gr_2C.out_degree(n) for n in gr_2C.nodes()] #Out-degree

#Function to calculate degree frequencies to return degree frequency and highest node
def calculate_deg_freq(G, d, flag):

    if flag:
        degree_list = [G.in_degree(n) for n in G.nodes()]
        highest_node = np.argmax(degree_list)+1    #node of maximum corresponding degree
    else:
        degree_list = [G.out_degree(n) for n in G.nodes()]
        highest_node = np.argmax(degree_list)+1    #node of maximum corresponding degree

    max_deg = max(degree_list)+1
    freq = [ 0 for d in range(max_deg) ]
    for d in degree_list:
        freq[d] += 1
        
    return freq, highest_node

#Calling the function to get inward, outward degree frequencies and highest in/out nodes
#Flag variable is passed as True to denote inward_deg calculations, False for outward
in_degree_freq, high_in = calculate_deg_freq(gr_2C, in_degree, True) 
out_degree_freq, high_out = calculate_deg_freq(gr_2C, out_degree, False) 

#Plotting Graphs of Degree distribution frequencies
list_inward = [n/gr_2C.number_of_nodes() for n in in_degree_freq]
plt.bar(range(len(in_degree_freq)), list_inward, log=True, color='orange', edgecolor='black')
plt.xlabel('In Degree --->')
plt.ylabel('Probability Density --->')
plt.title('Degree distribution for In Degree', fontsize=10)
plt.grid()
plt.show()

print('---------------------------------------- IN DEGREE RESULTS ------------------------------')
print('Min: ', np.min(in_degree))
print('Max: ', np.max(in_degree))
print('Mean: ', np.mean(in_degree))
print('Highest In-degree Node: ', high_in)
print('-----------------------------------------------------------------------------------------')

list_outward = [n/gr_2C.number_of_nodes() for n in out_degree_freq]
plt.bar(range(len(out_degree_freq)), list_outward, log=True, color='orange', edgecolor='black')
plt.xlabel('Out-degree --->')
plt.ylabel('Probability Density --->)')
plt.title('Degree distribution for Out Degree', fontsize=10)
plt.grid()
plt.show()

print('---------------------------------------- OUT DEGREE RESULTS -----------------------------')
print('Min: ', np.min(out_degree))
print('Max: ', np.max(out_degree))
print('Mean: ', np.mean(out_degree))
print('Highest Out-degree Node: ', high_out)
print('-----------------------------------------------------------------------------------------')

#To list all the edges of the directed graph
list_of_edges = list(gr_2C.edges())

#Generating a list of random edges to be removed from the graph
num_of_edges=int(len(list_of_edges)/2)
rand_edge_list = sample(gr_2C.in_edges(), num_of_edges)

#Creating a copy of the graph to have the new graph with removed edges
gr_2C_updated = gr_2C.copy()
gr_2C_updated.remove_edges_from(rand_edge_list)

#Verifying the number of edges after removal
print('Number of edges in updated graph :', gr_2C_updated.number_of_edges())

#Generating degrees of each node as a list
in_degree = [gr_2C_updated.in_degree(n) for n in gr_2C_updated.nodes()] #In-degree
out_degree = [gr_2C_updated.out_degree(n) for n in gr_2C_updated.nodes()] #Out-degree

#Calling the function to get inward, outward degree frequencies and highest in/out nodes
#Flag variable is passed as True to denote inward_deg calculations, False for outward
in_degree_freq, high_in = calculate_deg_freq(gr_2C_updated, in_degree, True) 
out_degree_freq, high_out = calculate_deg_freq(gr_2C_updated, out_degree, False) 

#Plotting Graphs of Degree distribution frequencies
list_inward = [n/gr_2C_updated.number_of_nodes() for n in in_degree_freq]
plt.bar(range(len(in_degree_freq)), list_inward, log=True, color='magenta', edgecolor='black')
plt.xlabel('In Degree --->')
plt.ylabel('Probability Density --->')
plt.title('Degree distribution for In Degree', fontsize=10)
plt.grid()
plt.show()

print('------------------------------ RESULTS AFTER RANDOM EDGE REMOVAL ------------------------')

print('---------------------------------------- IN DEGREE RESULTS ------------------------------')
print('Min: ', np.min(in_degree))
print('Max: ', np.max(in_degree))
print('Mean: ', np.mean(in_degree))
print('Highest In-degree Node: ', high_in)
print('-----------------------------------------------------------------------------------------')

list_outward = [n/gr_2C_updated.number_of_nodes() for n in out_degree_freq]
plt.bar(range(len(out_degree_freq)), list_outward, log=True, color='magenta', edgecolor='black')
plt.xlabel('In Degree --->')
plt.ylabel('Probability Density --->')
plt.title('Degree distribution for Out Degree', fontsize=10)
plt.grid()
plt.show()

print('---------------------------------------- OUT DEGREE RESULTS -----------------------------')
print('Min: ', np.min(out_degree))
print('Max: ', np.max(out_degree))
print('Mean: ', np.mean(out_degree))
print('Highest Out-degree Node: ', high_out)
print('-----------------------------------------------------------------------------------------')

