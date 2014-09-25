import pygame
from pygame.locals import*

import math

from entity import Entity
from vector import Vector

class Bullet(Entity):

    VERTICES = (Vector(0,2), Vector(0,3))

    #CONSTRUCTOR
    def __init__(self, pos_x, pos_y, angle_rad, surface, dt):
        
        
        Entity.__init__(self, pos_x, pos_y, surface, dt, self.VERTICES)
        self.speed = 500.0
        self.angle = angle_rad
        self.time = 0.0
        self.velocity[0] = self.speed * math.cos(self.angle)
        self.velocity[1] = self.speed * math.sin(self.angle)

        
    #UPDATING AND ANIMATION
    def update(self):
        Entity.update(self)
        self.time += 1

    #DRAW IN PARENTCLASS
    def draw(self, surface):
        Entity.draw(self)
