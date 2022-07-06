# Fitness-Music-Counter
A simple android application for workout counting with radio music

# Basic Architecture
This project based on MVVM architecture with dagger Hilt.

Views = Main Acitivity, Setup Fragment, Count Fragment, Statistics Fragment

ViewModels = Shared ViewModel, Count Fragment View Model

Models = ROOM database, Shared Preferences, Radio Service


```mermaid
flowchart BT
subgraph Views
SetupFragment --> MainActivity
CountFragment --> MainActivity
StatFragment --> MainActivity
end

subgraph ViewModels
MainViewModel 
CountViewModel
end

subgraph Repository
Room[(Room)]
LocalSharedPreferences
id1{{RadioService}}
end

ViewModels --> Views
Repository --> ViewModels
```


# Radio sources
All radio streams from NRJ radio
