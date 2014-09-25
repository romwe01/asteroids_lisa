# moving spaceship with pygame
import pygame, sys, math, entity, player, entitysystem, messenger, state
from pygame.locals import*
from math import *
from vector import *
from entitysystem import *
from state import *

# set up pygame
pygame.init()
fpsClock = pygame.time.Clock()
    
# set up the window
display = pygame.display.set_mode((640,480),0,32)
pygame.display.set_caption('Asteroids Entity System!')

# set up the colors
WHITE = (255,255,255)

FPS = 60
DELTA_TIME = 1.0/FPS # konstant, wichtig float-Wert!
clock = pygame.time.Clock()

end_of_game = False

# entitysystem = EntitySystem();

entitysystem.init(DELTA_TIME, display)





pygame.display.flip()

# States
splash = SplashState('splash', pygame, display)
splash.activate()
#splash.deactivate()
#splash.activate()

end = TestState('end')
end.deactivate()

play = PlayState('play', entitysystem);
play.activate();

# unterschied zwischen transitionTo und activate? 

# setup StateMachine
sm = StateMachine()
# sm.start_state = splash
# sm.end_state = end

sm.setStartEnd(splash, end)
sm.addState(play);
sm.addTransition(splash, 'x', play) # bei event x
#sm.addTransition(play, 'x', end) # bei event x
# messenger.subscribe('x');
messenger.subscribe(lambda:sm.handle('x'), "x");

sm.activate()

#teststates = {};
#teststates['src1'] = {};
#teststates['src2'] = {};
#teststates['src1']['msgX'] = 'destination1x'
#teststates['src1']['msgY'] = 'destination1y'
#teststates['src2']['msgZ'] = 'destination2z'

# enable termination of application from each single state
# messenger.subscribe(sm.handle(end), 'end')


# game loop
while not end_of_game:

    display.fill((0,0,0))

    
 #   entitysystem.handle_input();
    
    
 #   entitysystem.update();
 #   entitysystem.render();
 #   messenger.send("render");
    messenger.update();

    # Delay addition and removal of entites by one game loop cycle
 #   while len(entitysystem.functions) > 0:
 #       entitysystem.functions.pop(0)()

    

    sm.update(display);
    clock.tick(FPS)
    # pygame specific game loop epilog
    pygame.display.update()
 


