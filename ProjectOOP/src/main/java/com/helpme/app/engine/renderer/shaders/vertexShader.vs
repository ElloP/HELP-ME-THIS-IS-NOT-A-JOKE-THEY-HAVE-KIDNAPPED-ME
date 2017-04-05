#version 330 core

layout (location = 0) in vec3 position;

uniform float test;

out vec4 myColor;

void main() 
{
    gl_Position = vec4(position.x, position.y, position.z, 1.0f);
    myColor = vec4(0.6f,test,0.3f,1.0f);
}