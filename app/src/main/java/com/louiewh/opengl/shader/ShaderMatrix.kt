package com.louiewh.opengl.shader

import android.opengl.GLES20


open class ShaderMatrix: Texture2DRender() {

    private val verticesSource =
        """#version 300 es
                layout (location = 0) in vec3 aPos; 
                layout (location = 1) in vec3 aColor; 
                layout (location = 2) in vec2 aTexCoord; 
            
                out vec3 ourColor; 
                out vec2 TexCoord; 
                uniform mat4 uMatrix;                

                void main() 
                { 
                    gl_Position = uMatrix * vec4(aPos, 1.0); 
                    TexCoord = aTexCoord; 
                }"""


    private val fragmentSource =
        """#version 300 es
            out vec4 FragColor; 

            in vec3 ourColor; 
            in vec2 TexCoord; 

            uniform sampler2D ourTexture; 
             
            void main() 
            { 
                FragColor = texture(ourTexture, TexCoord);
            }"""

    protected var uMatrix = 0

    override fun getVertexSource(): String {
        return verticesSource
    }

    override fun getFragmentSource(): String {
        return fragmentSource
    }

    override fun onInitGLES(program: Int) {
        super.onInitGLES(program)

        uMatrix   = GLES20.glGetUniformLocation(program, "uMatrix")
    }
}