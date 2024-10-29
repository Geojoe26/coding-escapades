# -*- coding: utf-8 -*-
"""
Created on Tue Apr 11 01:20:23 2023

@author: Joel George
"""

import networkx as nx
import matplotlib.pyplot as plt
import numpy as np
import random as rm
from scipy import stats as st

G = nx.read_edgelist('C:/Users/joelg/Desktop/Coursework Sem 2/NS/Coursework 2/netscience.mtx')
print(nx.info(G))

G_rand = nx.gnm_random_graph(G.number_of_nodes(),G.number_of_edges(),seed=220291367)

Gr = G.copy()
Gr_rand = G_rand.copy()

lst_S = []
lst_comp = []
lst_S_rand = []
lst_comp_rand = []
count=0
count_rand=0
flag = True
def blah(Graph):

    V = list(Graph.nodes())
    N = len(V)
    
    # Random Failures
    N_failed = 10

    failed_nodes = rm.sample(V, N_failed)
    H = Graph.copy()
    H.remove_nodes_from(failed_nodes)
    
    return H

while(Gr.number_of_nodes() > 10):

    Gr = blah(Gr)
    count+=10
    Gcc = sorted(nx.connected_components(Gr), key=len, reverse=True)
    S = len(Gcc[0])/len(G)
    S_rem = count/len(G)
    
    lst_S.append(S)
    lst_comp.append(S_rem)
    
    nodes = len(Gcc[0])

    print(S)
    
while(Gr_rand.number_of_nodes() > 10):

    Gr_rand = blah(Gr_rand)
    count_rand+=10 
    Gcc_rand = sorted(nx.connected_components(Gr_rand), key=len, reverse=True)
    S_rand = len(Gcc_rand[0])/len(G_rand)
    S_rand_rem = count_rand/len(G)
    
    lst_S_rand.append(S_rand)
    lst_comp_rand.append(S_rand_rem)
  
plt.plot(lst_comp,lst_S, color='brown', label='Original n/w')
plt.plot(lst_comp_rand,lst_S_rand, color='green', label='Random n/w')
plt.xlabel('Nodes --->')
plt.ylabel('Robust --->')
plt.title('Plot 1')
plt.legend()
plt.show()