# -*- coding: utf-8 -*-
"""
Created on Mon Sep 30 17:28:29 2024

@author: Joel George

To read a file from URL which contains X & Y co-ordinates of a pattern and the output is
to decipher the final output word.
"""

import pandas as pd
from bs4 import BeautifulSoup


def readURL(url):
    #Reading the table from the URL
    data = pd.read_html(url, header=0, flavor='bs4')
    
    #Sorting the data by y and the x values, so that they can be printed in the correct order
    dataSort = data[0].sort_values(by=["y-coordinate","x-coordinate"], ignore_index=True)
    
    #Setting all the values so that they can be looped individually
    x = dataSort['x-coordinate']
    y = dataSort['y-coordinate']
    char = dataSort['Character']
    
    #Looping to print the values to the respective coordinates handling linebreaks and spaces
    for i in range(1, len(y)):
        if ((x[i] == 12) & (y[i] == 0)):
            print(" ", end='')
        if x[i] - x[i - 1] != 1:
            print(" " * int((x[i]) - (x[i - 1]) - 1), end='')
        if (y[i] != (y[i - 1])):
            print('\r')
        print (char[i], end='') 
    
    #Print new line after exiting each loop
    print('\n')  

#Method call to read the file by passing URL
readURL("https://docs.google.com/document/d/e/2PACX-1vSHesOf9hv2sPOntssYrEdubmMQm8lwjfwv6NPjjmIRYs_FOYXtqrYgjh85jBUebK9swPXh_a5TJ5Kl/pub")        
