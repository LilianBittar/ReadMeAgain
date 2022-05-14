package com.lilianbittar.readmeagain.network;

import com.lilianbittar.readmeagain.model.Book;
import java.util.List;

public interface CallbackLoading {
    void call(List<Book> searchResult);
}
