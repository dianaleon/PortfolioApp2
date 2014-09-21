package com.portfolio2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.portfolio.activities.HomeActivity;
import com.portfolio.activities.TextActivity;
import com.portfolio.listener.IPortfolioListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IMenu;
import com.portfolio.utils.Config;

public class MainActivity extends Activity implements IPortfolioListener {

	public String tittleApp = null;
	public String subtittleApp = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Config.URL = "http://www.appsworld.eu/apps/ios/appsworld2.json";
		PortfolioModel portfolioModel = PortfolioModel.getInstance(this);
		portfolioModel.getPortfolio(this);
	}

	@Override
	public void onPortfolioReady() {
		PortfolioModel portfolioModel = PortfolioModel.getInstance(this);
		IMenu menu = portfolioModel.getPorfolioMenu();
		menu.getTitle();
		tittleApp = menu.getTitle();
		subtittleApp = menu.getSubtitle();
		menu.getBackground();

		Intent intent = new Intent(MainActivity.this, HomeActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void errorGetPortfolio() {
		Intent intent = new Intent(MainActivity.this, TextActivity.class);
		intent.putExtra("text", "");
		startActivity(intent);
	}
}