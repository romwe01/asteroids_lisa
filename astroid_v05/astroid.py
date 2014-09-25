#!/usr/bin/env python
import pygame
from pygame.locals import *

import math
import random

from entity import Entity
from vector import Vector

class Astroid(Entity):
    
    VERTICES = (Vector(-42, 5),
                Vector(-25, 18),
                Vector(-20, 30),
                Vector(0, 35),
                Vector(15, 19),
                Vector(25, 20),
                Vector(40, 0),
                Vector(30, -10),
                Vector(30, -30),
                Vector(0, -40),
                Vector(-10, -30),
                Vector(-35, -25),
                Vector(-35, -10))
    
    #CONSTRUCTOR
    def __init__(self, pos_x, pos_y, surface, dt):
        Entity.__init__(self, pos_x, pos_y, surface, dt, self.VERTICES)
        
        self.angle = random.uniform(0.0, 360.0)
        self.spin = random.uniform(-1.0, 1.0)
        self.speed = 50.0
        #self.scale = Vector(5, 5)
        self.velocity = Vector(self.speed * random.uniform(-1.0, 1.0), self.speed * random.uniform(-1.0, 1.0))
        
        #RADIUS AND NUMBER OF EDGES OF ASTROIDS
        #self.radius = 20
        #num_points = random.randint(3,8)
        
        #for i in range(num_points):
        #    angle = (i * 2* math.pi)/num_points
        #    point = Vector(math.cos(angle), math.sin(angle))
        #    self.rel_points.append(point)
        
        #self.angle = (self.angle + self.spin) % 360.0
        
        #angle_rad = math.radians(self.angle)
            
        #self.real_points = []
        #for vec in self.rel_points:
        #    sized = Vector(vec[0] * self.radius, vec[1] * self.radius)
        #    rotated = sized.rotated(angle_rad)
        #    self.real_points.append([rotated[0] + self.position[0], rotated[1] + self.position[1]])

        
    def update(self):
        Entity.update(self)
        
        
        self.angle = (self.angle + self.spin) % 360.0    
        #angle_rad = math.radians(self.angle)
            
        #self.real_points = []
        #for vec in self.rel_points:
        #    sized = Vector(vec[0] * self.radius, vec[1] * self.radius)
        #    rotated = sized.rotated(angle_rad)
        #    self.real_points.append([rotated[0] + self.position[0], rotated[1] + self.position[1]])
            
            
    def draw(self):
        Entity.draw(self)
        
    def handle_input(self, key):
        #if (key == ""):
        #    print "Error"
        if (key == "render"):
            self.draw()
