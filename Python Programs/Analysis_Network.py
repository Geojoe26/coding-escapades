# -*- coding: utf-8 -*-
"""
Created on Fri Mar 17 02:18:59 2023

@author: Joel George
"""
import networkx as nx
import matplotlib.pyplot as plt

#Reading the dataset selected for the exercise 1, the file name is socfb-Simmons81.mtx
dataset_graph = nx.read_edgelist('C:/Users/joelg/Desktop/Coursework Sem 2/NS/socfb-Simmons81.mtx')

# On analysing the graph network, it was found that it is not a connected graph. 
# Hence, we proceed with the largest component so we separate the biggest network chunk 
# from the main network.
dataset_graph_subset = max(nx.connected_components(dataset_graph))

# Creating a subgraph that includes only the nodes in the gaint connected component
dataset_subgraph = dataset_graph.subgraph(dataset_graph_subset)

# compute betweenness centrality for every edges
betweenness_centrality = nx.betweenness_centrality(dataset_graph)

# plot betweenness distribution
plt.hist(betweenness_centrality.values(), bins=40, color='red', edgecolor='black')
plt.xlabel('Betweenness_centrality of edges')
plt.ylabel('No. of Edges')
plt.title('Betweenness Distribution')
plt.grid()
plt.show()

# plot betweenness profile
sorted_centrality = sorted(betweenness_centrality.items(), key=lambda x: x[1], reverse=True)
betweenness_values = [y for z, y in sorted_centrality]
cumulative_centrality = [sum(betweenness_values[:i]) for i in range(1, len(betweenness_values)+1)]
plt.plot(cumulative_centrality, color='blue')
plt.xlabel('No. of Edges')
plt.ylabel('Cumulative Edges')
plt.title('Betweenness Profile')
plt.grid()
plt.show()