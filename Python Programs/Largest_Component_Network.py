# -*- coding: utf-8 -*-
"""
Created on Fri Mar 17 02:15:15 2023

@author: Joel George
"""

import networkx as nx

#Reading the dataset selected for the exercise 1, the file name is socfb-Simmons81.mtx
dataset_graph = nx.read_edgelist('C:/Users/joelg/Desktop/Coursework Sem 2/NS/socfb-Simmons81.mtx')

# On analysing the graph network, it was found that it is not a connected graph. 
# Hence, we proceed with the largest component so we separate the biggest network chunk 
# from the main network.
dataset_graph_subset = max(nx.connected_components(dataset_graph))

# Creating a subgraph that includes only the nodes in the gaint connected component
dataset_subgraph = dataset_graph.subgraph(dataset_graph_subset)

r_before = nx.degree_assortativity_coefficient(dataset_subgraph)
bc = nx.betweenness_centrality(dataset_subgraph)
dataset_subgraph_updated = dataset_subgraph.copy()
top_nodes = sorted(bc, key=bc.get, reverse=True)[:int(dataset_subgraph.number_of_nodes()/2)]
dataset_subgraph_updated.remove_nodes_from(top_nodes)
r_after = nx.degree_assortativity_coefficient(dataset_subgraph_updated)
n_components_after = nx.number_connected_components(dataset_subgraph_updated)
n_components_before = nx.number_connected_components(dataset_subgraph)
print(f"Assortativity by degree before node removal: {r_before:.4f}")
print(f"Assortativity by degree after node removal: {r_after:.4f}")
print(f"Number of connected components before node removal: {n_components_before}")
print(f"Number of connected components after node removal: {n_components_after}")