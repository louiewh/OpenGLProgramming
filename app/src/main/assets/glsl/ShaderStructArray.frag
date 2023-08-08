#version 300 es

precision mediump float;

in vec4 outColor;
out vec4 gl_FragColor;

void main() {
    gl_FragColor = outColor;
}
