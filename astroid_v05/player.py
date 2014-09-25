#!/usr/bin/env python
import pygame
from pygame.locals import *

import math

from entity import Entity
from vector import Vector
from bullet import Bullet

class Player(Entity):
    
    VERTICES = (Vector(1,-1), Vector(0,0), Vector(-1, -1), Vector(0,1))
    
    #CONSTRUCTOR
    def __init__(self, pos_x, pos_y, surface,dt):
        Entity.__init__(self, pos_x, pos_y, surface, dt, self.VERTICES)
        self.alive = True
        self.angle = 180
        self.speed = 300
        
        #list of (angle,radius) pairs for the better looks
        #self.rel_points = [[0,20], [-140, 20],[180, 7.5],[140, 20]]
        self.scale = Vector(7,7)
        
        #make arrow shape
        #for i in range(len(self.rel_points)):
        #    self.rel_points[i] = (math.radians(self.rel_points[i][0]), scale * self.rel_points[i][1])

        #self.bullets = []
        #self.fire = 0.0


        #self.real_points = []
        #for point_angle, point_radius in self.rel_points:
        #    angle = math.radians(self.angle) + point_angle
        #    xp = point_radius * math.sin(angle)
        #    yp = point_radius * math.cos(angle)
        #    self.real_points.append((self.position[0] + xp, self.position[1] + yp))
        
    def transform(self):
        Entity.transform(self)
        
    #UPDATING AND ANIMATION
    def update(self):
        Entity.update(self)
        
        #for b in self.bullets:
        #    b.update(dt, screen_size)
        #    if b.time > 5.0:
        #        self.bullets.remove(b)
        #        continue
        
       
        
        #if self.fire > 0.0:
        #    self.fire -= dt
        
        #for point_angle, point_radius in self.rel_points:
        #    angle = math.radians(self.angle) + point_angle
        #    xp = point_radius * math.sin(angle)
        #    yp = point_radius * math.cos(angle)
        #    self.real_points.append((self.position[0] + xp, self.position[1] + yp))
        
        
    def draw(self):
        #for b in self.bullets:
        #    b.draw()
            
        if self.alive:
            Entity.draw(self)

    #for additional deceleration
    def demp(self):
        self.velocity *= 0.99

    def accelerate(self):
        self.velocity[0] += self.dt* self.speed * math.sin(math.radians(self.angle))
        self.velocity[1] += self.dt* self.speed * math.cos(math.radians(self.angle))
        self.demp()
        
    def decelerate(self):
        self.velocity *= 0.97
        
        
    def turnRight(self):
        self.angle -=3.0
        
    def turnLeft(self):
        self.angle += 3.0
    
    def shoot(self):
        bullet = Bullet(self.position[0], self.position[1], self.angle, self.surface, dt)
        return bullet
    
    def handle_input(self, key):
        if (key == "left"):
            self.angle += 3
        elif(key == "right"):
            self.angle -=3
        elif (key == "accelerate"):
            self.accelerate()
        elif (key == "deaccelerate"):
            self.decelerate()
        elif(key == "render"):
            self.draw()
        elif(key == "shoot"):
            self.shoot()