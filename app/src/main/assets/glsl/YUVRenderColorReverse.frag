#version 300 es

out vec4 FragColor;
in vec2 textureCoord;

uniform sampler2D textureY;
uniform sampler2D textureU;
uniform sampler2D textureV;

void main()
{
    float y, u, v;
    vec3 rgb;
    y = texture(textureY, textureCoord).r;
    u = texture(textureU, textureCoord).g - 0.5;
    v = texture(textureV, textureCoord).b - 0.5;
    rgb.r = 1.0f - (y + 1.540 * v);
    rgb.g = 1.0f - (y - 0.183 * u - 0.459 * v);
    rgb.b = 1.0f - (y + 1.818 * u);
    FragColor = vec4(rgb, 1.0);
}

