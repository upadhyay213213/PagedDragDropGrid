/**
 * Copyright 2012 
 * 
 * Nicolas Desjardins  
 * https://github.com/mrKlar
 * 
 * Facilite solutions
 * http://www.facilitesolutions.com/
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.evol.home.launcher.pageddragdropgrid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.evol.home.launcher.appPreferences.AppSharedPreferences;
import com.evol.home.launcher.lib.OnPageChangedListener;
import com.evol.home.launcher.lib.PagedDragDropGrid;

import java.util.ArrayList;
import java.util.List;


public class ExampleActivity extends Activity implements OnClickListener {
    
    private String CURRENT_PAGE_KEY = "CURRENT_PAGE_KEY";
    
    private PagedDragDropGrid gridview;

    private List<Page> pages = new ArrayList<Page>();
    private Page page1,page2,page3;
    private List<Item> page1Items,page2Items,page3Items;
    private AppSharedPreferences prefs;
    private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);

        context = this;
		gridview = (PagedDragDropGrid) findViewById(R.id.gridview);

        prefs = AppSharedPreferences.getInstance(this);

        addItemsToPage1();
        addItemsToPage2();
        addItemsToPage3();

        ExamplePagedDragDropGridAdapter adapter = new ExamplePagedDragDropGridAdapter(this, gridview,pages);

        gridview.setAdapter(adapter);
		gridview.setClickListener(this);

		gridview.setBackgroundColor(Color.LTGRAY);

		gridview.setOnPageChangedListener(new OnPageChangedListener() {
            @Override
            public void onPageChanged(PagedDragDropGrid sender, int newPageNumber) {
                // Toast.makeText(ExampleActivity.this, "Page changed to page " + newPageNumber, Toast.LENGTH_SHORT).show();
            }
        });
        gridview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(context,"First Long click",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
	}

    private void addItemsToPage1() {
        if (prefs.getPage1()!=null) {
            page1 = prefs.getPage1();
            //Toast.makeText(this, " Persisted Data 1", Toast.LENGTH_SHORT).show();
        } else {
            page1 = new Page();
            page1Items = new ArrayList<Item>();
            page1Items.add(new Item(1, "Item 1", R.drawable.ic_launcher));
            page1Items.add(new Item(2, "Item 2", R.drawable.ic_launcher));
            page1Items.add(new Item(3, "Item 3", R.drawable.ic_launcher));
            page1.setItems(page1Items);
        }

        pages.add(0, page1);
    }

    private void addItemsToPage2() {
        if (prefs.getPage2() != null) {
            page2 = prefs.getPage2();
            //Toast.makeText(this, " Persisted Data 2", Toast.LENGTH_SHORT).show();
        } else {
            page2 = new Page();
            page2Items = new ArrayList<Item>();
            page2Items.add(new Item(4, "Item 4", R.drawable.ic_launcher));
            page2Items.add(new Item(5, "Item 5", R.drawable.ic_launcher));
            page2Items.add(new Item(6, "Item 6", R.drawable.ic_launcher));
            page2.setItems(page2Items);
        }

        pages.add(1, page2);
    }

    private void addItemsToPage3() {
        if (prefs.getPage3()!=null) {
            page3 = prefs.getPage3();
            //Toast.makeText(this, " Persisted Data 3", Toast.LENGTH_SHORT).show();
        } else {
            page3 = new Page();
            page3Items = new ArrayList<Item>();
            page3Items.add(new Item(7, "Item 7", R.drawable.ic_launcher));
            page3Items.add(new Item(8, "Item 8", R.drawable.ic_launcher));
            page3Items.add(new Item(9, "Item 9", R.drawable.ic_launcher));
            page3.setItems(page3Items);
        }

        pages.add(2, page3);
    }
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	  super.onRestoreInstanceState(savedInstanceState);
      int savedPage = savedInstanceState.getInt(CURRENT_PAGE_KEY);
      gridview.restoreCurrentPage(savedPage);
	}
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {

	    outState.putInt(CURRENT_PAGE_KEY, gridview.currentPage());
        prefs.setPage1(page1);
        prefs.setPage2(page2);
        prefs.setPage3(page3);

	    super.onSaveInstanceState(outState);
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Reset").setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                gridview.setAdapter(new ExamplePagedDragDropGridAdapter(ExampleActivity.this, gridview, pages));
                gridview.notifyDataSetChanged();

                return true;
            }
        });

        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        prefs.setPage1(page1);
        prefs.setPage2(page2);
        prefs.setPage3(page3);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Clicked View", Toast.LENGTH_SHORT).show();
    }
}
