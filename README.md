# Read me

| Task                                                            | Finished | 
|--------------------------------------------------------------|-----------|
| When the app is first launched, fetch the recipes JSON from the API and store it in the app DB.       | ✔️ | 
| On subsequent launches, use the data from the local DB.                                               | ✔️ | 
| Have a 5-minute recurring background job to sync the local DB with the API                            | ✔️ | 
| Be able to mark a recipe(s) as favorite                                                               | ✔️ | 
| Be able to sort and re-arrange the list then the change will be saved to the local DB.                | ✔️ |

---
- #### When the app is first launched, fetch the recipes JSON from the API and store it in the app DB.

- ####  On subsequent launches, use the data from the local DB.
```bash
//get data from database
mRecipeViewModel.readAllData.observe(this, Observer { response ->
    if(response.isEmpty()){
        viewModel.getRecipe()
        viewModel.myResponse.observe(this, Observer {
            if (it.isSuccessful) {
                val items = it.body()
                if (items != null) {
                    mRecipeViewModel.addRecipe(items)
                }
            }
        })
    }
    listData = response
    adapter.setData(response)
})
```
- ####  Have a 5-minute recurring background job to sync the local DB with the API.
```bash
fun repeatGetData() {

    timer.scheduleAtFixedRate(
        object : TimerTask() {

            override fun run() {
                try {
                    viewModel.getRecipe()
                    mRecipeViewModel.addRecipe(viewModel.myResponse.value!!.body() as List<Recipe>)
                    Log.d("Repeat", "insert data success")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }, 0, 300000
    )   // 1000 Millisecond  = 1 second
}
```

- #### Be able to mark a recipe(s) as favorite.
```bash
favoriteButton.setOnClickListener {
    data.favorite = !data.favorite
    mRecipeViewModel.updateRecipe(data)
    if(data.favorite){
        DrawableCompat.setTint(buttonDrawable, Color.RED)
    }else{
        DrawableCompat.setTint(buttonDrawable, Color.GRAY)
    }
}
```

- #### Be able to sort and re-arrange the list then the change will be saved to the local DB.
```bash
//Re-arrange
private var simpleCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP.or(ItemTouchHelper.DOWN),0){
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val startPosition = viewHolder.adapterPosition
        val endPosition = target.adapterPosition

        Collections.swap(listData, startPosition, endPosition)
        recyclerView.adapter?.notifyItemMoved(startPosition, endPosition)
        return true

    }
```

```bash
//Sort
override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.sortByName){
        listData = listData.sortedBy { it.name }
    }else if(item.itemId == R.id.sortByCalories){
        listData = listData.sortedBy { it.calories }
    }
    mRecipeViewModel.addRecipe(listData)
    return super.onOptionsItemSelected(item)
}
```
