_subscribers = {}   # leeres dictionary --> assoziatives array --> set keine liste verwenden
_queue = []
_commands = []



# _xxx --> interne struktur
def subscribe(subscriber, msg_type):
#    def foo(subscriber, msg_type):
     if msg_type in _subscribers:
         _subscribers[msg_type].append(subscriber)
     else:
         _subscribers[msg_type] = [subscriber]

#    _commands.append(foo)

def unsubscribe(subscriber, msg_type = None):
    if msg_type == None:
        for s in _subscribers:
            if subscriber in _subscribers[s]:
                _subscribers[s].remove(subscriber)
    else:
        _subscribers[msg_type].remove(subscriber)

def fire(msg_type, param = None):
    if msg_type not in _subscribers:
        return

    for subscriber in _subscribers[msg_type]:
   #     subscriber(msg_type, param)
   #     msg_type()
   #     subscriber.handle_input(msg_type);
        subscriber()


def send(msg_type, param = None):
    
#    _queue.append((msg_type, param))
    def foo():
        fire(msg_type, param)
# check function pointer
    _queue.append(foo)

def update():
    for msg in _queue:
#        fire(msg[0], msg[1])
        msg()

    _queue[:] = []  # referenz der queue bleibt gleich, range wird mit leerer liste ausgetauscht, zB _queue[2:10] = [], [:] --> anfang bis ende