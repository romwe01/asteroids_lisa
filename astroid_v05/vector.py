import math

class Vector:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    # methode ueberschreiben fuer's debuggen wichtig
    def __repr__(self):
        return 'Vector(%s, %s)' % (self.x, self.y)

    def __len__(self):
        return 2

    def length_squared(self):
        return self.x * self.x + self.y * self.y

    def length(self):
        return math.sqrt(self.length_squared())

    # in-place
    def normalize(self):
        l = self.length()
        self.x /= 1
        self.y /= 1

    def normalized(self):
        l = self.length()
        return Vector(self,x / l, self.y / l)

    def rotated(self, phi):
        cos_phi = math.cos(phi)
        sin_phi = math.sin(phi)

        x = cos_phi * self.x - sin_phi * self.y
        y = sin_phi * self.x + cos_phi * self.y

        return Vector(x,y)

    def scaled(self, scale):
        return Vector(self.x * scale[0], self.y * scale[1])

    def translated(self, trans):
        return Vector(self.x + trans.x, self.y + trans.y)

    def transformed(vt, scale, angle, offset):

        vt = scaled(vt, scale)
        vt = rotated(vt, angle)
        vt = translated(vt, offset)

        return vt

    
        

    # jetzt kann ich auch mit v[0] auf self.x zugreifen oder eben mit v.x
    def __getitem__(self, key):
        if key == 0:
            return self.x
        elif key == 1:
            return self.y
        else:
            raise IndexError('vector index out of range')

    def __setitem__(self, key, value):
        if key == 0:
            self.x = value
        elif key == 1:
            self.y = value
        else:
            raise IndexError('vector index out of range')


    def __mul__(self, other):
        # if object has getitem method, then I can muliplicate
        # the elements of that object
        if hasattr(other, "__getitem__"):
            return Vector(self.x * other[0], self.y * other[1])
        else:
            return Vector(self.x * other, self.y * other)

    def __imul__(self, other):
        if hasattr(other, "__getitem__"):
            self.x *= other[0]
            self.y *= other[1]
        else:
            self.x *= other
            self.y *= other
        return self

    # function pointer, wenn zB 5 die Fkt mul nicht kennt,
    # dann sieht es bei v bei rmul nach und dieser zeigt zum mul
    __rmul__ = __mul__

    def __add__(self, other):
        if hasattr(other, "__getitem__"):
            return Vector(self.x + other, self.y + other)

    def __iadd__(self, other):
        if hasattr(other, "__getitem__"):
            self.x += other[0]
            self.y += other[1]
        else:
            self.x += other
            self.y += other
        return self

    __radd__ = __add__

    def __sub__(self, other):
        if hasattr(other, "__getitem__"):
            return Vector(self.x - other, self.y - other)

    def __isub__(self, other):
        if hasattr(other, "__getitem__"):
            self.x -= other[0]
            self.y -= other[1]
        else:
            self.x -= other
            self.y -= other
        return self

    __rsub__ = __sub__