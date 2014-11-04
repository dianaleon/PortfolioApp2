package com.portfolio2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.portfolio2.R;
import com.portfolio.activities.HomeActivity2;
import com.portfolio.activities.TextActivity;
import com.portfolio.components.menu;
import com.portfolio.listener.IPortfolioListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IMenu;
import com.portfolio.util.MenuBuilder2;
import com.portfolio.util.UIUtils;
import com.portfolio.utils.Config;


public class MainActivity extends Activity implements IPortfolioListener {

	public String tittleApp = null;
	public String subtittleApp = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Config.URL = "http://www.appsworld.eu/apps/android/arquitectoJoseph/appsworldArquitecto.json";	
		//Config.URL = "https://dl.dropboxusercontent.com/u/49247770/PortfolioApps/ArquitectoApp/appData.json";
		PortfolioModel portfolioModel = PortfolioModel.getInstance(this);
		portfolioModel.getPortfolio(this);
		setContentView(R.layout.body_home);
		
		UIUtils.setMenuBuilder(new MenuBuilder2());
	
		TextView textHome = (TextView)
			findViewById(R.id.homeText);
	
		textHome.setText("Josep Diran Arquitecto");
	
		
		 View splash_layout =  findViewById(android.R.id.content);
		
	
		 splash_layout.setBackgroundResource(R.drawable.home_img);
		// Fill Content
		
	}

	@Override
	public void onPortfolioReady() {
		PortfolioModel portfolioModel = PortfolioModel.getInstance(this);
		IMenu menu = portfolioModel.getPorfolioMenu();
		menu.getTitle();
		tittleApp = menu.getTitle();
		subtittleApp = menu.getSubtitle();
		menu.getBackground();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent intent = new Intent(MainActivity.this, HomeActivity2.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void errorGetPortfolio() {
//		Intent intent = new Intent(MainActivity.this, TextActivity.class);
//		intent.putExtra("text", "");
//		startActivity(intent);
		msgErrorConnection();
	}
	
	private void msgErrorConnection(){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this);
 
			// set title
			alertDialogBuilder.setTitle("No se pudo conectar");
 
			// set dialog message
			alertDialogBuilder
				.setMessage("¿Desea reintentar la conexión?")
				.setCancelable(false)
				.setPositiveButton("Reintentar",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
						
						
						PortfolioModel portfolioModel = PortfolioModel.getInstance(MainActivity.this);
						portfolioModel.getPortfolio(MainActivity.this);
						
					}
				  })
				.setNegativeButton("Salir",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						MainActivity.this.finish();
						//dialog.cancel();
					}
				});
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
	}
}