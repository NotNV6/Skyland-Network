# Skyland Network 
This is the main repository for the Skyland Network.

# Developer Rules/Guide
* We use finals whenever possible (not required for parameters)
* Document your functions/methods and constructors.
* Put all utility classes inside of the main [Commons](https://github.com/NotNV6/Skyland-Network/tree/master/Commons) plugin.
* Use proper commit messages
  * Use proper [emoji conventions](https://gist.github.com/parmentf/035de27d6ed1dce0b36a)
  * Add a brief description of what you changed, example: "added KoTH base structure"

# Modules
We use a maven modular system for our network.

* [Rank](https://github.com/NotNV6/Skyland-Network/tree/master/README.md#L12) - Created and maintained by NV6
* [PluginHandler](https://github.com/NotNV6/Skyland-Network/tree/master/README.md#L28) - Created and maintained by NV6
* [Commons](https://github.com/NotNV6/Skyland-Network/tree/master/README.md#L40) - Created and maintained by NV6


# [Ranks](https://github.com/NotNV6/Skyland-Netowrk/tree/master/Ranks)
General plugin for ranks/grants. 

- Features:
  - 
  * Temporary grants (untested as of M06 AP 22 PM)
  * Chat handler
  * Ranks
  * Staff/Default/Normal rank types.
  
# [PluginHandler](https://github.com/NotNV6/Skyland-Netowrk/tree/master/PluginHandler)
Plugin for enabling/disabling plugins

* Features:
  -
  * Disabling (soft) dependent plugins
  * Using chat procedures.
  
# [Commons](https://github.com/NotNV6/Skyland-Network/tree/master/Commons)
General library for all plugins.

- Features:
  -
  * Command API
  * General PlayerData / Data object for easy data saving/loading without having to rewrite lots of code.
  * API for chat procedures
  * Menu Library
  * Utility classes
  * And more to come later.