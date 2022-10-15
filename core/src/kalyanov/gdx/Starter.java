package kalyanov.gdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import org.graalvm.compiler.loop.MathUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Starter extends ApplicationAdapter {
	SpriteBatch batch;

	private Panzer myPanzer;
	private List<Panzer> enemies = new ArrayList<>();

	private KeyboardAdapter inputProcessor = new KeyboardAdapter();
	
	@Override
	public void create () {
		Gdx.input.setInputProcessor(inputProcessor);
		batch = new SpriteBatch();

		myPanzer = new Panzer(100, 200);
		List<Panzer> newEnemies = IntStream.range(0, 15)
				.mapToObj(i -> {
					int x = MathUtils.random(Gdx.graphics.getWidth());
					int y = MathUtils.random(Gdx.graphics.getHeight());
					return new Panzer(x, y, "Panzer_enemy.png");
				})
				.collect(Collectors.toList());
		enemies.addAll(newEnemies);
	}

	@Override
	public void render () {
		myPanzer.moveTo(inputProcessor.getDirection());
		myPanzer.rotateTo(inputProcessor.getMousePosition());
		ScreenUtils.clear(1, 1, 1, 1);
		batch.begin();
		myPanzer.render(batch);
		enemies.forEach(enemy -> {
			enemy.render(batch);
		});
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		myPanzer.dispose();
	}
}
