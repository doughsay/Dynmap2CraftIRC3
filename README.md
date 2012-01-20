# Dynmap2CraftIRC3
Do you use both dynmap and CraftIRC together?  If so you will have noticed that people chatting in the IRC channel will not see any messages that were sent from dynmap's webchat feature and vice versa.  This annoyed me since it created many awkward looking one sided conversations, and just caused general confusion among my players.  So I decided to fix it!

## What does this do?
This plugin connects CraftIRC 3 to dynmap's webchat.  More specifically, it creates a new tag and endpoint visible to CraftIRC 3.  With this plugin loaded, you can specify communications paths in your CraftIRC 3 config.yml to point to the tag "dynmap" to allow for chat flow between dynmap's webchat and any IRC channels that CraftIRC may be connected to.

## How do I use it?
### Dependencies
First, you need CraftIRC of at least version 3.1 and dynmap at least version 0.29.  It may work with lower versions of dynmap, but that hasn't been tested.  It definitely won't work with CraftIRC 2.

### Setup
The only configuration required is in CraftIRC's config.yml.  If you have:

    setting:
        ...
        auto-paths: true
        ...

Then you don't need to do anything else, it should automatically connect your dynmap webchat with IRC chat.  If it's false, and you're manually specifying your communications paths, then you will need to set up some new paths for this to work:

    path:
        ...
      - source: 'yourirctag'
        target: 'dynmap'

      - source: 'dynmap'
        target: 'yourirctag'
        ...

### Other configuration
The dynmap webchat endpoint is configured as a "plain" source, under the tag "generic".  So if you wanted to format how the messages appeared, you would add a section under formatting called "from-plain" with a "generic" option, like so:

    formatting:
        from-game:
            ...
        from-irc:
            ...
        from-plain:
            generic: '%green%[%source%]%o% %sender%: %message%'

## Screenshots
This is what it looks like when you're chatting across the webchat, the game, and an IRC channel:

### Dynmap Webchat
![Dynmap webchat screenshot](http://dev.bukkit.org/media/images/37/669/dynmap.png)

### IRC channel
![IRC chat screenshot](http://dev.bukkit.org/thumbman/images/37/670/600x76/irc.png.-m1.png)