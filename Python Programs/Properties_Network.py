# -*- coding: utf-8 -*-
"""
Created on Fri Mar 17 00:04:31 2023

@author: Joel George
"""
import networkx as nx
import matplotlib.pyplot as plt
import numpy as np

#Reading the dataset selected for the exercise 1, the file name is socfb-Simmons81.mtx
dataset_graph = nx.read_edgelist('C:/Users/joelg/Desktop/Coursework Sem 2/NS/socfb-Simmons81.mtx')

# On analysing the graph network, it was found that it is not a connected graph. 
# Hence, we proceed with the largest component so we separate the biggest network chunk 
# from the main network.
dataset_graph_subset = max(nx.connected_components(dataset_graph))

# Creating a subgraph that includes only the nodes in the gaint connected component
dataset_subgraph = dataset_graph.subgraph(dataset_graph_subset)

# Below are the properties for Real Networks mentioned in 1B
# High Clustering: This can be evaluated using the networkx function for clustering co-efficient
high_coeff_cluster = nx.average_clustering(dataset_graph)

# Small World Property: Can be figured out using the shortest path length method
avg_shortest_path = nx.average_shortest_path_length(dataset_subgraph)

# Scale Free Property: Plotting the degree distribution to analyse the network
# Generating a list of node degrees for the subgraph generated.
list_deg=[dataset_subgraph.degree(n) for n in dataset_subgraph.nodes()]
# Creating an array of zeros for deg with max node
arr_deg_nodes= np.zeros(max(list_deg)+1)
for val in list_deg:
    arr_deg_nodes[val] = arr_deg_nodes[val]+1

print('----------------------- PROPERTIES OF REAL NETWORKS VALUES ------------------------------')
print("Value of High Clustering (Clustering-Coeffiecient): ", high_coeff_cluster)
print("Value of Average shortest path length (Small World Property): ", avg_shortest_path)
print('-----------------------------------------------------------------------------------------')
# Graph of degree distribution
degree_dist_plt = [n/dataset_subgraph.number_of_nodes() for n in arr_deg_nodes]
plt.bar(range(len(arr_deg_nodes)), degree_dist_plt, color='darkgreen', alpha = 0.5)
plt.xlabel('Node Degrees ---->')
plt.ylabel('Probability Density ---->')
plt.title('Graph of Degree Distribution to depict Scale Free Property', fontsize=10)
plt.grid()
plt.show()