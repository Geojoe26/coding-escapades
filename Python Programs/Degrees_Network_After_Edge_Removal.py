# -*- coding: utf-8 -*-
"""
Created on Wed Mar 15 22:38:05 2023

@author: Joel George
"""
import networkx as nx
import matplotlib.pyplot as plt
import numpy as np
from scipy import stats as st
import networkx as nx
from random import sample

nodes = 200         #N
prob_rewire = 0.7   #p
k = 10              #nearest_neighbour
rand_seed = 6   #generating the random seed

#generating_graph
gr_2C = nx.watts_strogatz_graph(nodes, k, prob_rewire, rand_seed)

#Converting the graph to a directed graph using DiGraph
gr_2C = nx.DiGraph(gr_2C)

#Generating degrees of each node as a list
in_degree = [gr_2C.in_degree(n) for n in gr_2C.nodes()] #In-degree
out_degree = [gr_2C.out_degree(n) for n in gr_2C.nodes()] #Out-degree

#Verifying the number of edges after removal
print('Number of edges in the graph :', gr_2C.number_of_edges())

#Generating degrees of each node as a list
def calculate_deg_freq(G, d, flag):
    if flag:
        degreeseq = [G.in_degree(n) for n in G.nodes()]
        max_node = np.argmax(degreeseq)+1
    
    else:
        degreeseq = [G.out_degree(n) for n in G.nodes()]
        max_node = np.argmax(degreeseq)+1

    degreemax=max(degreeseq)+1
    freq= [ 0 for d in range(degreemax) ]
    for d in degreeseq:
        freq[d] += 1
        
    return freq, max_node

#Calling the function to get inward, outward degree frequencies and highest in/out nodes
#Flag variable is passed as True to denote inward_deg calculations, False for outward
inwards_deg_freq, highest_in_node = calculate_deg_freq(gr_2C, in_degree, True) 
outwards_deg_freq, highest_out_node = calculate_deg_freq(gr_2C, out_degree, True) 

#Plotting degree frequencies
#In-degree
in_frac = [n/gr_2C.number_of_nodes() for n in inwards_deg_freq]
plt.bar(range(len(inwards_deg_freq)), in_frac, log=True, color='magenta')
plt.xlabel('In-degree')
plt.ylabel('P(k)')
plt.title('In-degree distribution', fontsize=15)
plt.show()
print('Minimum In-degree: ', np.min(in_degree))
print('Maximum In-degree: ', np.max(in_degree))
print('Average In-degree: ', np.average(in_degree))
print('Node with highest In-degree: ', highest_in_node)

#Out-degree
out_frac = [n/gr_2C.number_of_nodes() for n in outwards_deg_freq]
plt.bar(range(len(outwards_deg_freq)), out_frac, log=True, color='magenta')
plt.xlabel('Out-degree')
plt.ylabel('P(k)')
plt.title('Out-degree distribution', fontsize=15)
plt.show()
print('---------------------------------------- OUT DEGREE RESULTS -----------------------------')
print('Min Degree: ', np.min(out_degree))
print('Max Degree: ', np.max(out_degree))
print('Average Degree: ', np.average(out_degree))
print('Node with highest Out-degree: ', highest_out_node)
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
in_degree_up = [gr_2C_updated.in_degree(n) for n in gr_2C_updated.nodes()] #In-degree
out_degree_up = [gr_2C_updated.out_degree(n) for n in gr_2C_updated.nodes()] #Out-degree

#Fuction call to degree_freq() to generate in, out and total degree frequencies
in_degree_freq_up, max_in_up = calculate_deg_freq(gr_2C_updated, in_degree_up, True) 
out_degree_freq_up, max_out_up = calculate_deg_freq(gr_2C_updated, out_degree_up, False) 

#Plotting degree frequencies
#In-degree
in_frac_up = [n/gr_2C_updated.number_of_nodes() for n in in_degree_freq_up]
plt.bar(range(len(in_degree_freq_up)), in_frac_up, log=True, color='orange')
plt.xlabel('In-degree')
plt.ylabel('P(k)')
plt.title('In-degree distribution', fontsize=15)
plt.show()
print('------------------------------ RESULTS AFTER RANDOM EDGE REMOVAL ------------------------')
print('---------------------------------------- IN DEGREE RESULTS ------------------------------')
print('Min Degree: ', np.min(in_degree))
print('Max Degree: ', np.max(in_degree))
print('Mean Degree: ', np.mean(in_degree))
print('Node with highest In-degree: ', highest_in_node)
print('-----------------------------------------------------------------------------------------')

#Out-degree
out_frac_up = [n/gr_2C_updated.number_of_nodes() for n in out_degree_freq_up]
plt.bar(range(len(out_degree_freq_up)), out_frac_up, log=True, color='orange')
plt.xlabel('Out-degree')
plt.ylabel('P(k)')
plt.title('Out-degree distribution', fontsize=15)
plt.show()
print('---------------------------------------- OUT DEGREE RESULTS -----------------------------')
print('Min Degree: ', np.min(out_degree))
print('Max Degree: ', np.max(out_degree))
print('Mean Degree: ', np.mean(out_degree))
print('Node with highest degree: ', highest_out_node) 
print('-----------------------------------------------------------------------------------------')
