
#version 300 es

out vec4 FragColor;
in vec2 textureCoord;

uniform sampler2D textureY;
uniform sampler2D textureU;
uniform sampler2D textureV;

void main() {
    vec3 yuv;
    yuv.x = texture(textureY, textureCoord).r;
    yuv.y = texture(textureU, textureCoord).r - 0.5;
    yuv.z = texture(textureV, textureCoord).r - 0.5;

    vec3 rgb = mat3(
        1.0, 1.0, 1.0,
        0.0, -0.344, 1.770,
        1.403, -0.714, 0.0) * yuv;

    // vec3 rgb;
    // rgb.r = y + 1.540 * v;
    // rgb.g = y - 0.183 * u - 0.459 * v;
    // rgb.b = y + 1.818 * u;

    FragColor = vec4(rgb, 1.0);
}
