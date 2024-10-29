import matplotlib.pyplot as plt
import networkx as nx
import numpy as np
import random as rm
from networkx.algorithms import community as cm

G = nx.read_edgelist('C:/Users/joelg/Desktop/Coursework Sem 2/NS/Coursework 2/netscience.mtx')

gn_partitions = list(cm.girvan_newman(G))

quality_gn = {n: cm.modularity(G, gn_partitions[n]) for n in range(len(gn_partitions))}
quality_values = list(quality_gn.values())

maxi = max(quality_values)
tuple_value = quality_values.index(maxi)

community = gn_partitions[tuple_value]
sorted_comm = sorted(community, key=len)

index_S = rm.choice(list(sorted_comm[0]))
index_M = rm.choice(list(sorted_comm[9]))
index_L = rm.choice(list(sorted_comm[17]))

t = np.arange(0, 50, 0.1)

# Initial conditions
S0 = 376
I0 = 3
R0 = 0

S = np.zeros(len(t))
I = np.zeros(len(t))
R = np.zeros(len(t))

# Set initial values
S[0] = S0
I[0] = I0
R[0] = R0
    
V = G.nodes()
N = len(V)

alpha = 0.6  # probability of S becoming I if an I neighbor is present
beta = 0.4   # probability of I becoming R
gamma = 0.2  # probability of R becoming S due to loss of immunity
delta = 0.2  # probability of spontaneous infection

# states = {v: 'I' if (v == index_L) else 'S' for v in V}
states = {v: 'I' if rm.random()<alpha else 'S' for v in V}
nx.set_node_attributes(G, states, 'state')

stop = False

while not stop:
    stop = True
    for v in V:
        # Transition Rules
        
        # S -> I
        if states[v] == 'S':
            for j in G.neighbors(v):
                if states[j] == 'I' and rm.random() < alpha:
                    states[v] = 'I'
                    stop = False
                    break
        
        # I -> R
        elif states[v] == 'I' and rm.random() < beta:
            states[v] = 'R'
            stop = False
        
        # R -> S
        elif states[v] == 'R' and rm.random() < gamma:
            states[v] = 'S'
            stop = False
        
        # spontaneous infection
        elif states[v] == 'S' and rm.random() < delta:
            states[v] = 'I'
            stop = False
    
# Euler method for numerical integration
for i in range(1, len(t)):
    dSdt = -beta * S[i-1] * I[i-1] / N + delta * R[i-1]
    dIdt = beta * S[i-1] * I[i-1] / N - gamma * I[i-1]
    dRdt = gamma * I[i-1] - delta * R[i-1]
    S[i] = S[i-1] + dSdt * (t[i] - t[i-1])
    I[i] = I[i-1] + dIdt * (t[i] - t[i-1])
    R[i] = R[i-1] + dRdt * (t[i] - t[i-1])
    
plt.plot(t, S, label='Susceptible')
plt.plot(t, I, label='Infected')
plt.plot(t, R, label='Recovered')
plt.xlabel('Time')
plt.ylabel('Population')
plt.legend()
plt.show()


