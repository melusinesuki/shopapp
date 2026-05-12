// index.js
Page({
  data: {
    name: "melusine",
    products:[]
  },
  onLoad(){
    this.listProduct();
  
  },
  onShow(){

  },
  listProduct(){
    wx.request({
      url:"http://localhost:8080/product/list",
      success:(resp)=>{
        this.setData({products:resp})
      }
    })
  },
  toDetail(e){
    const productName=e.currentTarget.dataset.name;
    console.assert.log(productName)
    wx.navigateTo({
        url:'/pages/index/product-detail/index?name='+productName
      })
  }

})
