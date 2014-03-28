package msi.gama.jogl.scene;

import static javax.media.opengl.GL.GL_TEXTURE_2D;
import msi.gama.jogl.utils.JOGLAWTGLRenderer;
import com.jogamp.opengl.util.texture.Texture;

public class MyTexture {

	private final Texture texture;
	private final boolean isDynamic;

	public MyTexture(final Texture texture, final boolean isDynamic) {
		super();
		this.texture = texture;
		this.isDynamic = isDynamic;
	}

	public void bindTo(final JOGLAWTGLRenderer renderer) {
		// renderer.getContext().makeCurrent();
		renderer.gl.glEnable(GL_TEXTURE_2D);
		texture.enable(renderer.gl);
		texture.bind(renderer.gl);
		// renderer.getContext().release();
	}

	public void unbindFrom(final JOGLAWTGLRenderer renderer) {
		// renderer.getContext().makeCurrent();
		renderer.gl.glDisable(GL_TEXTURE_2D);
		texture.disable(renderer.gl);
	}

	public boolean isDynamic() {
		return isDynamic;
	}

	public Texture getTexture() {
		return texture;
	}

	public void dispose(final JOGLAWTGLRenderer renderer) {
		texture.destroy(renderer.gl);
	}

}