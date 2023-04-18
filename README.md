# OUO - With Task
Welcome With Task!

OUO(With Task) is a task timer for minecraft and it can do something when a server is running.

## Let's us starts!
Good! OUO running like OVO (With Json) and you should create the dir and json by your self.

Now let's us create a dir with name "ouo" in your server loot dir.

And now create your json with any named!

If you want use the daily cycle, please mark ``time`` in format. Like this:
```Json
{
    "type": "message",
    "time": "1:00",
    "once": false,
    "message": "Test!"
}
```

If you just want it run once, mark ``once`` is true.

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
<td>Send a message for all the player if online.</td>
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
<td>Clean all the item in world. <br/>
Changed the blacklist(or whitelist) in config.json in this dir.</td>
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
    <td>Clean all the entity in the world without player.</td>
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
    <td>Do a event to a player. It still under development</td>
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
    <td>The blacklist(Or whitelist) for item entity. Why here no entity? The entity in 1.7.10 have no registryname and it's id ...<br />
    Must mark the file named is <code>config.json</code>> !
</td>
  </tr>
</table>


