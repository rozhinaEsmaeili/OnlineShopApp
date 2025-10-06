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
import com.example.onlineshopapp2.repositories.customer.UserRepository
import com.example.onlineshopapp2.repositories.invoices.InvoiceRepository
import com.example.onlineshopapp2.repositories.invoices.TransactionRepository
import com.example.onlineshopapp2.repositories.products.ColorRepository
import com.example.onlineshopapp2.repositories.products.ProductCategoryRepository
import com.example.onlineshopapp2.repositories.products.ProductRepository
import com.example.onlineshopapp2.repositories.site.BlogRepository
import com.example.onlineshopapp2.repositories.site.ContentRepository
import com.example.onlineshopapp2.repositories.site.SliderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserRepository(api: UserApi) = UserRepository(api)

    @Provides
    @Singleton
    fun provideInvoiceRepository(api: InvoiceApi) = InvoiceRepository(api)

    @Provides
    @Singleton
    fun provideTransactionRepository(api: TransactionApi) = TransactionRepository(api)


    @Provides
    @Singleton
    fun provideColorRepository(api: ColorApi) = ColorRepository(api)


    @Provides
    @Singleton
    fun provideProductCategoryRepository(api: ProductCategoryApi) = ProductCategoryRepository(api)

    @Provides
    @Singleton
    fun provideProductRepository(api: ProductApi) = ProductRepository(api)

    @Provides
    @Singleton
    fun provideBlogRepository(api: BlogApi) = BlogRepository(api)

    @Provides
    @Singleton
    fun provideContentRepository(api: ContentApi) = ContentRepository(api)

    @Provides
    @Singleton
    fun provideSliderRepository(api: SliderApi) = SliderRepository(api)

}