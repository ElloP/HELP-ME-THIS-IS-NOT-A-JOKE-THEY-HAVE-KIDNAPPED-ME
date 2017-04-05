#version 330 core

layout (location = 0) in vec3 position;

uniform float test;

out vec4 myColor;

void main() 
{
    myColor = vec4(clamp(position, 0.0, test), 1.0f);
    gl_Position = vec4(position.x, position.y, position.z, 1.0f);
}