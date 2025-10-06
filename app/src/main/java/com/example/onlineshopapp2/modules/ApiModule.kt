package com.example.onlineshopapp2.modules

import com.example.onlineshopapp2.api.customers.UserApi
import com.example.onlineshopapp2.api.invoices.InvoiceApi
import com.example.onlineshopapp2.api.invoices.TransactionApi
import com.example.onlineshopapp2.api.products.ColorApi
import com.example.onlineshopapp2.api.products.ProductApi
import com.example.onlineshopapp2.api.products.ProductCategoryApi
import com.example.onlineshopapp2.api.site.BlogApi
import com.example.onlineshopapp2.api.site.ContentApi
import com.example.onlineshopapp2.api.site.SliderApi
import com.example.onlineshopapp2.config.OnlineShopApplication
import com.example.onlineshopapp2.config.UnsafeSSLConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://onlineshop.holosen.net")
            .addConverterFactory(GsonConverterFactory.create())
            .client(UnsafeSSLConfig.unsafeOkHttpClient.build())
            .build()
    }


    @Provides
    @Singleton
    fun provideUserApi(): UserApi {
        return provideApi().create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideInvoiceApi(): InvoiceApi {
        return provideApi().create(InvoiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTransactionApi(): TransactionApi {
        return provideApi().create(TransactionApi::class.java)
    }

    @Provides
    @Singleton
    fun provideColorApi(): ColorApi {
        return provideApi().create(ColorApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return provideApi().create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductCategoryApi(): ProductCategoryApi {
        return provideApi().create(ProductCategoryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideblogApi(): BlogApi {
        return provideApi().create(BlogApi::class.java)
    }

    @Provides
    @Singleton
    fun provideContentApi(): ContentApi {
        return provideApi().create(ContentApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSliderApi(): SliderApi {
        return provideApi().create(SliderApi::class.java)
    }


}