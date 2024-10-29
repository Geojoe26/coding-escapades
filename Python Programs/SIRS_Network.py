import numpy as np
import networkx as nx
import matplotlib.pyplot as plt
from networkx.algorithms import community as cm

# Parameters
alpha = 0.6  # Immunity loss rate
beta = 0.4  # Infection rate
gamma = 0.2  # Recovery rate
delta = 0.2  # Random infection rate

# Define graph with 3 communities
G = nx.read_edgelist('C:/Users/joelg/Desktop/Coursework Sem 2/NS/Coursework 2/netscience.mtx')
gn_partitions = list(cm.girvan_newman(G))

quality_gn = {n: cm.modularity(G, gn_partitions[n]) for n in range(len(gn_partitions))}
quality_values = list(quality_gn.values())

maxi = max(quality_values)
tuple_value = quality_values.index(maxi)

partn = gn_partitions[tuple_value]

# Initial values
N = G.number_of_nodes()
S = np.ones(N)
I = np.zeros(N)
R = np.zeros(N)

# Helper function to infect a node
def infect(node):
    if S[node] == 1:
        S[node] = 0
        I[node] = 1

# Simulation
def simulate(infected_community):
    # Random initial condition
    for node in range(N):
        if G.nodes[node]['block'] == infected_community:
            infect(node)
            break

    # Store populations over time
    S_list = [np.sum(S) / N]
    I_list = [np.sum(I) / N]
    R_list = [np.sum(R) / N]

    for _ in range(49):
        for node in range(N):
            if I[node] == 1:
                # Recovery
                if np.random.rand() < gamma:
                    I[node] = 0
                    R[node] = 1
                # Random infection
                elif np.random.rand() < delta:
                    infect(node)
                else:
                    # Infection by neighbors
                    neighbors = list(G.neighbors(node))
                    for neighbor in neighbors:
                        if S[neighbor] == 1 and np.random.rand() < beta:
                            infect(neighbor)

        # Immunity loss
        for node in range(N):
            if R[node] == 1 and np.random.rand() < alpha:
                R[node] = 0
                S[node] = 1

        # Store populations
        S_list.append(np.sum(S) / N)
        I_list.append(np.sum(I) / N)
        R_list.append(np.sum(R) / N)

    return S_list, I_list, R_list

# Plotting
plt.figure(figsize=(12, 4))

plt.subplot(131)
for i in range(100):
    S, I, R = simulate(45)
    plt.plot(range(50), S, color='blue')
    plt.plot(range(50), I, color='red')
    plt.plot(range(50), R, color='green')
plt.title('Infected in largest community')

plt.subplot(132)
for i in range(100):
    S, I, R = simulate(185)
    plt.plot(range(50), S, color='blue')
    plt.plot(range(50), I, color='red')
    plt.plot(range(50), R, color='green')
plt.title('Infected in smallest community')