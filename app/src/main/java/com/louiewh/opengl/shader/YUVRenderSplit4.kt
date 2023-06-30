package com.louiewh.opengl.shader

class YUVRenderSplit4:YUVRender() {

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
                u = texture(textureU, textureCoord).g - 0.5;
                v = texture(textureV, textureCoord).b - 0.5;
                
                rgb.r = y + 1.540*v;
                rgb.g = y - 0.183*u - 0.459*v;
                rgb.b = y + 1.818*u;
                    
                if(textureCoord.x <= 0.5 && textureCoord.y <= 0.5){
                
                    //左上角，使用反色
                    float r = 1.0 - rgb.r;
                    float g = 1.0 - rgb.g;
                    float b = 1.0 - rgb.b;
                    FragColor = vec4(r, g, b, 1.0);
                }else if(textureCoord.x > 0.5 && textureCoord.y > 0.5){
                
                    //右下角，使用灰度
                    float gray = rgb.r * 0.2126 + rgb.g * 0.7152 + rgb.b * 0.0722;
                    FragColor = vec4(gray, gray, gray, 1.0);
                }else{
                    FragColor = vec4(rgb, 1.0);
                }
            }
    """

    override fun getFragmentSource(): String {
        return fragmentSource
    }
}