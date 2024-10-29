# -*- coding: utf-8 -*-
"""
Created on Sun Sep 24 13:48:51 2023

@author: Joel George
"""

import pandas as pd
import numpy as np
from datetime import datetime
import matplotlib.pyplot as plt
from statsmodels.graphics.tsaplots import plot_acf, plot_pacf
from statsmodels.tsa.api import VAR
from scipy.stats import pearsonr

def parser(s):
    return datetime.strptime(s, '%Y-%m')

ice_cream_heater_df = pd.read_csv('FDI_1.csv', parse_dates=[0], index_col=0, squeeze=True, date_parser=parser)
ice_cream_heater_df = ice_cream_heater_df.asfreq(pd.infer_freq(ice_cream_heater_df.index))

plt.figure(figsize=(12,6))
ice_cream, = plt.plot(ice_cream_heater_df['FDI'])
heater, = plt.plot(ice_cream_heater_df['Price'], color='red')

for year in range(2003, 2022):
    plt.axvline(datetime(year,1,1), linestyle='--', color='k', alpha=0.5)

plt.legend(['FDI', 'Price'], fontsize=16)

avgs = ice_cream_heater_df.mean()
devs = ice_cream_heater_df.std()

for col in ice_cream_heater_df.columns:
    ice_cream_heater_df[col] = (ice_cream_heater_df[col] - avgs.loc[col]) / devs.loc[col]
    
plt.figure(figsize=(12,6))
ice_cream, = plt.plot(ice_cream_heater_df['FDI'])
heater, = plt.plot(ice_cream_heater_df['Price'], color='red')

for year in range(2003, 2022):
    plt.axvline(datetime(year,1,1), linestyle='--', color='k', alpha=0.5)
    
plt.axhline(0, linestyle='--', color='k', alpha=0.3)

plt.legend(['FDI', 'Price'], fontsize=16)

ice_cream_heater_df = ice_cream_heater_df.diff().dropna()

plt.figure(figsize=(12,6))
ice_cream, = plt.plot(ice_cream_heater_df['FDI'])
heater, = plt.plot(ice_cream_heater_df['Price'], color='red')

for year in range(2003, 2022):
    plt.axvline(datetime(year,1,1), linestyle='--', color='k', alpha=0.5)
    
plt.axhline(0, linestyle='--', color='k', alpha=0.3)
plt.ylabel('First Difference', fontsize=18)

plt.legend(['FDI', 'Price'], fontsize=16)

plt.figure(figsize=(12,6))
ice_cream, = plt.plot(ice_cream_heater_df['FDI'])

for year in range(2003, 2022):
    plt.axvline(datetime(year,1,1), linestyle='--', color='k', alpha=0.5)
    
plt.axhline(0, linestyle='--', color='k', alpha=0.3)
plt.ylabel('First Difference', fontsize=18)

plt.legend(['FDI'], fontsize=16)

annual_volatility = ice_cream_heater_df.groupby(ice_cream_heater_df.index.year).std()
print(annual_volatility)

ice_cream_heater_df['fdi_annual_vol'] = ice_cream_heater_df.index.map(lambda d: annual_volatility.loc[d.year, 'FDI'])
ice_cream_heater_df['price_annual_vol'] = ice_cream_heater_df.index.map(lambda d: annual_volatility.loc[d.year, 'Price'])

ice_cream_heater_df['FDI'] = ice_cream_heater_df['FDI'] / ice_cream_heater_df['fdi_annual_vol']
ice_cream_heater_df['Price'] = ice_cream_heater_df['Price'] / ice_cream_heater_df['price_annual_vol']

plt.figure(figsize=(12,6))
ice_cream, = plt.plot(ice_cream_heater_df['FDI'])
heater, = plt.plot(ice_cream_heater_df['Price'], color='red')

for year in range(2003, 2022):
    plt.axvline(datetime(year,1,1), linestyle='--', color='k', alpha=0.5)
    
plt.axhline(0, linestyle='--', color='k', alpha=0.3)
plt.ylabel('First Difference', fontsize=18)

plt.legend(['FDI', 'Price'], fontsize=16)

month_avgs = ice_cream_heater_df.groupby(ice_cream_heater_df.index.month).mean()

print(month_avgs)

ice_cream_heater_df['fdi_month_avg'] = ice_cream_heater_df.index.map(lambda d: month_avgs.loc[d.month, 'FDI'])
ice_cream_heater_df['price_month_avg'] = ice_cream_heater_df.index.map(lambda d: month_avgs.loc[d.month, 'Price'])

print(ice_cream_heater_df)

ice_cream_heater_df['FDI'] = ice_cream_heater_df['FDI'] - ice_cream_heater_df['fdi_month_avg']
ice_cream_heater_df['Price'] = ice_cream_heater_df['Price'] - ice_cream_heater_df['price_month_avg']

print(ice_cream_heater_df)

plt.figure(figsize=(12,6))
ice_cream, = plt.plot(ice_cream_heater_df['FDI'])

for year in range(2003, 2022):
    plt.axvline(datetime(year,1,1), linestyle='--', color='k', alpha=0.5)
    
plt.axhline(0, linestyle='--', color='k', alpha=0.3)
plt.ylabel('First Difference', fontsize=18)

plt.legend(['FDI'], fontsize=16)

plt.figure(figsize=(12,6))
ice_cream, = plt.plot(ice_cream_heater_df['FDI'])
heater, = plt.plot(ice_cream_heater_df['Price'], color='red')

for year in range(2003, 2022):
    plt.axvline(datetime(year,1,1), linestyle='--', color='k', alpha=0.5)
    
plt.axhline(0, linestyle='--', color='k', alpha=0.3)
plt.ylabel('First Difference', fontsize=18)

plt.legend(['FDI', 'Price'], fontsize=16)

plot_pacf(ice_cream_heater_df['Price'])
plt.show()

ice_cream_heater_df = ice_cream_heater_df.replace(np.nan, 0)

for lag in range(1, 14):
    heater_series = ice_cream_heater_df['Price'].iloc[lag:]
    lagged_ice_cream_series = ice_cream_heater_df['FDI'].iloc[:-lag]
    print('Lag: %s'%lag)
    print(pearsonr(heater_series, lagged_ice_cream_series))
    print('------')

ice_cream_heater_df = ice_cream_heater_df[['FDI', 'Price']]
model = VAR(ice_cream_heater_df)
model_fit = model.fit(maxlags=13)
print(model_fit.summary())