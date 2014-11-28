package Objetos;

import Controladores.ColisionCursor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;

public class Cursor extends Actor{
	private MyGdxGame game;
	private Texture cursor;
	private TextureRegion[] arriba, abajo, derecha, izquierda;
	private TextureRegion[][] tmp;
	private Animation moverArriba, moverAbajo, moverDerecha, moverIzquierda;
	private TextureRegion frameActual;
	private float stateTime;
	private Vector2 velocity;
	private Rectangle limites;
	private ColisionCursor colisiones;
	
	public Cursor(MyGdxGame game) {
		this.game = game;
		
		//Creamos el grafico del cursor
		cursor = new Texture(Gdx.files.internal("Imagenes/personaje.png"));
		tmp = TextureRegion.split(cursor, cursor.getWidth() / 4, cursor.getHeight());
		arriba = new TextureRegion[3];
		arriba[0] = arriba[1] = arriba[2] = tmp[0][0];
		abajo = new TextureRegion[3];
		abajo[0] = abajo[1] = abajo[2] = tmp[0][2];
		derecha = new TextureRegion[3];
		derecha[0] = derecha[1] = derecha[2] = tmp[0][3];
		izquierda = new TextureRegion[3];
		izquierda[0] = izquierda[1] = izquierda[2] = tmp[0][1];
		
		frameActual = new TextureRegion(arriba[0]);
		
		stateTime = 0f;
		setPosition(500, 300);
		setWidth(cursor.getWidth() / 4);
		setHeight(cursor.getHeight());
		
		//Creamos la animación
		moverArriba = new Animation(0.3f, arriba);
		moverAbajo = new Animation(0.3f, abajo);
		moverIzquierda = new Animation(0.3f, izquierda);
		moverDerecha = new Animation(0.3f, derecha);
		
		velocity = new Vector2(0, 0);
		
		//Colisiones
		limites = new Rectangle();
		limites.setSize(cursor.getWidth() / 4, cursor.getHeight());
		//colisiones = new ColisionCursor(game.getPasillo());
	}
	
	//Dibujamos al actor
	public void draw(Batch batch, float parentAlpha){  
		batch.draw(frameActual, getX(), getY());
	}
	
	//Logica del actor
	/*public void act(float delta){
		//Me muevo
		if(Gdx.input.isKeyJustPressed(Keys.W) && !Gdx.input.isKeyJustPressed(Keys.A)
				&& !Gdx.input.isKeyJustPressed(Keys.S) && !Gdx.input.isKeyJustPressed(Keys.D)){
			
			if(colisiones.colisionaArriba()){
				velocity.y = 0;
			}else{
				velocity.y = 1;
				velocity.x = 0;
			}
			
			frameActual = moverArriba.getKeyFrame(stateTime,true);
			
			
		}else if(!Gdx.input.isKeyJustPressed(Keys.W) && !Gdx.input.isKeyJustPressed(Keys.A)
				&& Gdx.input.isKeyJustPressed(Keys.S) && !Gdx.input.isKeyJustPressed(Keys.D)){
			
				velocity.y = -1;
				velocity.x = 0;
				frameActual = moverAbajo.getKeyFrame(stateTime,true);
			
		}else if(!Gdx.input.isKeyJustPressed(Keys.W) && Gdx.input.isKeyJustPressed(Keys.A)
				&& !Gdx.input.isKeyJustPressed(Keys.S) && !Gdx.input.isKeyJustPressed(Keys.D)){

				velocity.x = -1;
				velocity.y = 0;
				frameActual = moverIzquierda.getKeyFrame(stateTime,true);
			
		}else if(!Gdx.input.isKeyJustPressed(Keys.W) && !Gdx.input.isKeyJustPressed(Keys.A)
				&& !Gdx.input.isKeyJustPressed(Keys.S) && Gdx.input.isKeyJustPressed(Keys.D)){
		
				velocity.x = 1;
				velocity.y = 0;
				frameActual = moverDerecha.getKeyFrame(stateTime,true);
		
		}else{
			velocity.x = 0;
			velocity.y = 0;
		}

		setX(getX() + velocity.x);
		setY(getY() + velocity.y);
      
        //actualizamos nuestro stateTime y dibujamos el currentFrame.
        stateTime += delta;
        
        //Actualizamos la posición de las colisiones
        limites.setPosition(getX(), getY());
	}
	*/
	public void setStateTime(float time){
		stateTime += time;
	}
	
	public float getStateTime(){
		return stateTime;
	}
	
	public Vector2 getVelocity(){
		return velocity;
	}
	
	public void setVelocityX(float x){
		velocity.x = x;
	}
	
	public void setVelocityY(float y){
		velocity.y = y;
	}
	
	public void MirarArriba(){
		frameActual = moverArriba.getKeyFrame(stateTime,true);
	}
	
	public void MirarAbajo(){
		frameActual = moverAbajo.getKeyFrame(stateTime,true);
	}
	
	public void MirarDerecha(){
		frameActual = moverDerecha.getKeyFrame(stateTime,true);
	}
	
	public void MirarIzquierda(){
		frameActual = moverIzquierda.getKeyFrame(stateTime,true);
	}
	
	public Rectangle getLimites(){
		return limites;
	}
}