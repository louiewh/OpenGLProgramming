package com.louiewh.opengl.shader

class YUVRenderLuma:YUVRender() {

    private  val fragmentSource =
        """#version 300 es
            out vec4  FragColor;
            in  vec2  textureCoord;

            uniform sampler2D textureY;
            uniform sampler2D textureU;
            uniform sampler2D textureV;

            void main()
            {
                float y,u,v;
                vec3 rgb;
                y = texture(textureY, textureCoord).r;
                u = 0.0f; // texture(textureU, textureCoord).g - 0.5;
                v = 0.0f; // texture(textureV, textureCoord).b - 0.5;
                rgb.r = y + 1.540*v;
                rgb.g = y - 0.183*u - 0.459*v;
                rgb.b = y + 1.818*u;
                FragColor = vec4(rgb, 1.0);
            }
    """

    override fun getFragmentSource(): String {
        return fragmentSource
    }
}