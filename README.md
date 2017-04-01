# SectionedRecyclerViewAdapter
An Adapter that allows a RecyclerView to be split into Sections with headers and/or footers.

[![Build Status](https://travis-ci.org/luizgrp/SectionedRecyclerViewAdapter.svg?branch=master)](https://travis-ci.org/luizgrp/SectionedRecyclerViewAdapter)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-SectionedRecyclerViewAdapter-green.svg?style=true)](https://android-arsenal.com/details/1/3471)

<img src="https://raw.githubusercontent.com/luizgrp/SectionedRecyclerViewAdapter/master/art/sc1.png" width="300" height="533" >
<img src="https://raw.githubusercontent.com/luizgrp/SectionedRecyclerViewAdapter/master/art/sc4.png" width="300" height="533" >
<br><br>

In addition, each Section can have its state(Loading/Loaded/Failed) controlled individually.

<img src="https://raw.githubusercontent.com/luizgrp/SectionedRecyclerViewAdapter/master/art/sc2.png" width="300" height="533" >
<img src="https://raw.githubusercontent.com/luizgrp/SectionedRecyclerViewAdapter/master/art/sc3.png" width="300" height="533" >

---

## Gradle Dependency

```groovy
dependencies {
    compile 'io.github.luizgrp.sectionedrecyclerviewadapter:sectionedrecyclerviewadapter:1.0.5'
}
```

## Basic usage:
##### 1) Create a custom Section class extending StatelessSection
```java
class MySection extends StatelessSection {

    List<String> myList = Arrays.asList("Item1", "Item2", "Item3");

    public MySection() {
        // call constructor with layout resources for this Section header and items 
        super(R.layout.section_header, R.layout.section_item);
    }

    @Override
    public int getContentItemsTotal() {
        return myList.size(); // number of items of this section
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        // return a custom instance of ViewHolder for the items of this section
        return new MyItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyItemViewHolder itemHolder = (MyItemViewHolder) holder;

        // bind your view here
        itemHolder.tvItem.setText(myList.get(position));
    }
}
```

##### 2) Create a custom ViewHolder for the section items:
```java
class MyItemViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvItem;
    
    public MyItemViewHolder(View itemView) {
        super(itemView);

        tvItem = (TextView) itemView.findViewById(R.id.tvItem);
    }
}
```

##### 3) Set up your ReclyclerView with the SectionedRecyclerViewAdapter
```java
// Create an instance of SectionedRecyclerViewAdapter 
SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

// Add your Sections
sectionAdapter.addSection(new MySection());

// Set up your RecyclerView with the SectionedRecyclerViewAdapter
RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
recyclerView.setAdapter(sectionAdapter);
```

## Demo app

You can find a demo app [here](app).

<img src="https://raw.githubusercontent.com/luizgrp/SectionedRecyclerViewAdapter/master/art/demosc.png" width="300" height="533" >

## Examples

Please check the implementation of the examples:

Stateless Section
- [Section with Header](app/src/main/java/io/github/luizgrp/sectionedrecyclerviewadapter/demo/Example1Fragment.java)
- [Section with Header and Footer](app/src/main/java/io/github/luizgrp/sectionedrecyclerviewadapter/demo/Example2Fragment.java)
- [Expandable Section](app/src/main/java/io/github/luizgrp/sectionedrecyclerviewadapter/demo/Example4Fragment.java)
- [Grid Section with Header](app/src/main/java/io/github/luizgrp/sectionedrecyclerviewadapter/demo/Example5Fragment.java)
- [Expandable Grid Section](app/src/main/java/io/github/luizgrp/sectionedrecyclerviewadapter/demo/Example6Fragment.java)
- [SearchView with Sections](app/src/main/java/io/github/luizgrp/sectionedrecyclerviewadapter/demo/Example7Fragment.java)
- [Animations](app/src/main/java/io/github/luizgrp/sectionedrecyclerviewadapter/demo/Example8Fragment.java)

Stateful Section
- [Section with Header and Footer](app/src/main/java/io/github/luizgrp/sectionedrecyclerviewadapter/demo/Example3Fragment.java)


## License

    The MIT License (MIT)
    
    Copyright (c) 2016 Gustavo Pagani
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
