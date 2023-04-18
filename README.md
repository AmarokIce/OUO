# OUO - With Task
Welcome to With Task!

OUO(With Task) implements a task timer for minecraft and it can do specific things when a server is running.

## Let's start!
Good! OUO runs like [OVO (With Json)](https://github.com/AmarokIce/WithJson) and you should create the dir and json by yourself.

Now let's create a dir with name "ouo" in your server root dir.

And now create your json with any name!

If you want a task to run in a daily cycle, please declare a ``time`` key in the block. Like this:
```Json
{
    "type": "message",
    "time": "1:00",
    "once": false,
    "message": "Test!"
}
```

If you just want it to run only once, make ``once``\'s value true.

<table>
    <tr>
        <th><i>Task(Type)</i></th>
        <th><i>Demo</i></th>
        <th><i>What is it</i></th>
    </tr>
    <tr>
        <td><code>message</code></td>
        <td>

```Json
{
    "type": "message",
    "time": "1",
    "once": false,
    "message": "Test!"
}
```
</td>
<td>Send a message to all online players.</td>
</tr>
  <tr>
    <td><code>cleanItem</code></td>
    <td>

```Json
{
  "type": "cleanItem",
  "time": "1",
  "once": false
}
```
</td>
<td>Clean all the items in the world. <br/>
Modify the blacklist(or whitelist) in config.json in the same dir.</td>
  </tr>
  <tr>
    <td><code>cleanEntity</code></td>
    <td>

```Json
{
  "type": "cleanItem",
  "time": "1",
  "once": false
}
```
</td>
    <td>Clean all the entities in the world, players excluded.</td>
  </tr>
  <tr>
    <td><code>event</code></td>
    <td>

```Json
{
    "type": "message",
    "time": "1",
    "once": false,
    "player": "someone",
    "event": {
      "give": "minecraft:apple",
      "message": "Give you an apple!"
    }
}
```
</td>
    <td>Do an event to a player(WIP).</td>
  </tr>
  <tr>
    <td><code>config</code></td>
    <td>

```Json
{
  "isWhiteList": false,
  "list": [
    "minecraft:apple"
  ]
}
```
</td>
    <td>The blacklist(or whitelist) for item entities. You may be confused as to why you can only set these for item entities. It's because the common entities in 1.7.10 have no registry name or id...<br />
    Must have the file named <code>config.json</code>> !
</td>
  </tr>
</table>


