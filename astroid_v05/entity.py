#!/usr/bin/env python
import pygame
from pygame.locals import*
import math

from vector import Vector

class Entity:
    
    def __init__(self, pos_x, pos_y, surface, dt, vertices):
        self.position = Vector(pos_x, pos_y)
        self.velocity = Vector(0,0)
        self.angle = 0.0
        self.scale = Vector(1,1)
        self.rel_points = []
        self.color = (255,255, 255)
        self.speed = 0
        self.surface = surface
        self.dt = dt
        self.vertices = vertices
        self.vertices_trans = []
        
        self.toDestroy = False
        
    def update(self):
        
        self.position += self.velocity * self.dt
        
        if self.position[0] < -0.5:
            self.position[0] = 640
        elif self.position[0] > 640:
            self.position[0] = 0
        if self.position[1] < -0.5:
            self.position[1] = 480
        elif self.position[1] > 480:
            self.position[1] = 0
        
        self.transform()
        
    def draw(self):
        if (len(self.vertices_trans) >0):
            last_vertex = self.vertices_trans[len(self.vertices)-1]
            for vertex in self.vertices_trans:
                pygame.draw.line(self.surface, self.color, vertex, last_vertex)
                last_vertex = vertex
        
    def handle_input(self, key):
        raise NotImplementedError
    
    def transform(self):
        vertices_trans = []
        for vt in self.vertices:
            vt = vt.scaled(self.scale)
            vt = vt.rotated(math.radians(self.angle))
            vt = vt.translated(self.position)
            vertices_trans.append(vt)
            
        self.vertices_trans = vertices_trans
        
        