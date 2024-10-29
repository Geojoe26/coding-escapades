# -*- coding: utf-8 -*-
"""
Created on Tue Apr 11 20:54:16 2023

@author: Joel George
"""

import matplotlib.pyplot as plt
import networkx as nx
import numpy as np
from networkx.algorithms import community as cm
from scipy.integrate import odeint

G = nx.read_edgelist('C:/Users/joelg/Desktop/Coursework Sem 2/NS/Coursework 2/netscience.mtx')

gn_partitions = list(cm.girvan_newman(G))

quality_gn = {n: cm.modularity(G, gn_partitions[n]) for n in range(len(gn_partitions))}
quality_values = list(quality_gn.values())

maxi = max(quality_values)
tuple_value = quality_values.index(maxi)

print(len(gn_partitions[tuple_value]))