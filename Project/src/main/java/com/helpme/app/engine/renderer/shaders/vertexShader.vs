#version 330 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 texCoords;

uniform mat4 model;
uniform mat4 projection;
uniform mat4 view;

out vec2 TexCoords;

void main() 
{
    TexCoords = vec2(texCoords.x, 1.0f - texCoords.y);
    gl_Position = projection * view * model * vec4(position.x, position.y, position.z, 1.0f);
}