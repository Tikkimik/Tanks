package kalyanov.gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Panzer {
    private final float size = 64;
    private final float halfSize = size / 2;

    private final Vector2 position = new Vector2();
    private final Vector2 angle = new Vector2();
    private final Texture texture;
    private final TextureRegion textureRegion;

    public Panzer(float x, float y) {
        this(x, y, "Panzer.png");
    }

    public Panzer(float x, float y, String textureName) {
        texture = new Texture(textureName);
        textureRegion = new TextureRegion(texture);
        position.set(x, y);
    }

    public void render(Batch butch) {
        butch.draw(
                textureRegion,
                position.x,
                position.y,
                halfSize,
                halfSize,
                size,
                size,
                1,
                1,
                angle.angleDeg()
        );
    }

    public void dispose() {
        texture.dispose();
    }

    public void moveTo(Vector2 direction) {
        position.add(direction);
    }

    public void rotateTo(Vector2 mousePosition) {
        angle.set(mousePosition).sub(position.x + halfSize, position.y + halfSize);
    }
}
