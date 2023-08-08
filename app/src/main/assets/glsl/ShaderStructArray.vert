#version 300 es

in vec3 vPosition;
in vec3 inColor;
out vec4 outColor;
void main() {
    gl_Position = vec4(vPosition, 1.0);
    outColor = vec4(inColor, 1.0);
}
