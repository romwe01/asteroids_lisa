import pygame, random, messenger
from pygame.locals import*
from vector import *
from entity import *
from player import *
from bullet import *
from astroid import *

# class EntitySystem():

entities = []
functions = []


def init(dt, display):
        
    initialize_ship(dt, display)
    initialize_asteroids(dt, display)
    


def initialize_ship(dt, display):
    
    ship = Player(640/2,480/2, display, dt)
    add(ship)

    messenger.subscribe(ship.shoot, "shoot");
    messenger.subscribe(ship.accelerate, "accelerate");
    messenger.subscribe(ship.decelerate, "deaccelerate");
    messenger.subscribe(ship.turnRight, "right");
    messenger.subscribe(ship.turnLeft, "left");
    messenger.subscribe(ship.draw, "render");

def initialize_asteroids(DELTA_TIME, display):
    random.seed()
    amount = random.randint(5,10)
        
    for i in range(0, amount, 1):
        asteroid = Astroid(random.randint(0, 640), random.randint(0, 480), display, DELTA_TIME)
        add(asteroid)
        

def add(entity):
    messenger.subscribe(entity.draw, "render");
    functions.append(lambda:entities.append(entity))


def remove(entity):
    functions.append(lambda:entities.remove(entity))
    messenger.unsubscribe(entity.draw)


def rungame():

    display.fill((0,0,0))

    handle_input();
    update();
    draw();

    # Delay addition and removal of entites by one game loop cycle
    while len(functions) > 0:
        functions.pop(0)()

    # pygame specific game loop epilog
    #pygame.display.update()
    #DELTA_TIME = fpsClock.tick() / 1000.0
    
 

def update():
    for entity in entities:
        if entity.toDestroy == True:
            remove_entity(entity)
        entity.update()
 
def draw():
#    for entity in entities:
#        entity.render()
    messenger.send("render");
    pass

def handle_input():
    for event in pygame.event.get():
            
        if event.type == QUIT:
            pygame.quit()
            sys.exit()
        elif event.type == KEYDOWN and (event.key == K_SPACE or pygame.key.get_mods() & KMOD_SHIFT):
            for entity in entities:
                if isinstance(entity, Ship):
                    add(entity.shoot())
                    messenger.send("shoot")
                    break

    keys = pygame.key.get_pressed();

    if keys[K_LEFT]: 
        messenger.send("left")
         
    if keys[K_RIGHT]:
        messenger.send("right")
        
    if keys[K_UP]:  
        messenger.send("accelerate")
    if keys[K_x]:
        print("x pressend")
        messenger.send('x');
    else: 
        messenger.send("decelerate")   
             

def render_gui():
    print()