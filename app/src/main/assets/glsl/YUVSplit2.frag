#version 300 es
precision mediump float;

out vec4 FragColor;
in vec2 textureCoord;

uniform sampler2D textureY;
uniform sampler2D textureU;
uniform sampler2D textureV;

void main()
{
    float y, u, v;
    vec3 rgb;

    //输入是不能被修改的，所以使用一个vec2 分量
    vec2 uv = textureCoord.xy;
    if (uv.y >= 0.0 && uv.y <= 0.5) {
        uv.y = uv.y + 0.25;
    } else {
        uv.y = uv.y - 0.25;
    }

    y = texture(textureY, uv).r;
    u = texture(textureU, uv).g - 0.5;
    v = texture(textureV, uv).b - 0.5;

    rgb.r = y + 1.540 * v;
    rgb.g = y - 0.183 * u - 0.459 * v;
    rgb.b = y + 1.818 * u;

    FragColor = vec4(rgb, 1.0);
}