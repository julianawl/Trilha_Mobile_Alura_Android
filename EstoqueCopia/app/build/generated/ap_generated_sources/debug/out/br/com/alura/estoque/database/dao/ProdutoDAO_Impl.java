package br.com.alura.estoque.database.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import br.com.alura.estoque.database.converter.BigDecimalConverter;
import br.com.alura.estoque.model.Produto;
import java.lang.Double;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class ProdutoDAO_Impl implements ProdutoDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfProduto;

  private final BigDecimalConverter __bigDecimalConverter = new BigDecimalConverter();

  private final EntityInsertionAdapter __insertionAdapterOfProduto_1;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfProduto;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfProduto;

  public ProdutoDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProduto = new EntityInsertionAdapter<Produto>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Produto`(`id`,`nome`,`preco`,`quantidade`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Produto value) {
        stmt.bindLong(1, value.getId());
        if (value.getNome() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNome());
        }
        final Double _tmp;
        _tmp = __bigDecimalConverter.paraDouble(value.getPreco());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindDouble(3, _tmp);
        }
        stmt.bindLong(4, value.getQuantidade());
      }
    };
    this.__insertionAdapterOfProduto_1 = new EntityInsertionAdapter<Produto>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Produto`(`id`,`nome`,`preco`,`quantidade`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Produto value) {
        stmt.bindLong(1, value.getId());
        if (value.getNome() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNome());
        }
        final Double _tmp;
        _tmp = __bigDecimalConverter.paraDouble(value.getPreco());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindDouble(3, _tmp);
        }
        stmt.bindLong(4, value.getQuantidade());
      }
    };
    this.__deletionAdapterOfProduto = new EntityDeletionOrUpdateAdapter<Produto>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Produto` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Produto value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfProduto = new EntityDeletionOrUpdateAdapter<Produto>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Produto` SET `id` = ?,`nome` = ?,`preco` = ?,`quantidade` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Produto value) {
        stmt.bindLong(1, value.getId());
        if (value.getNome() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNome());
        }
        final Double _tmp;
        _tmp = __bigDecimalConverter.paraDouble(value.getPreco());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindDouble(3, _tmp);
        }
        stmt.bindLong(4, value.getQuantidade());
        stmt.bindLong(5, value.getId());
      }
    };
  }

  @Override
  public long salva(Produto produto) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfProduto.insertAndReturnId(produto);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void salva(List<Produto> produtos) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfProduto_1.insert(produtos);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void remove(Produto produto) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfProduto.handle(produto);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void atualiza(Produto produto) {
    __db.beginTransaction();
    try {
      __updateAdapterOfProduto.handle(produto);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Produto> buscaTodos() {
    final String _sql = "SELECT * FROM Produto";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfNome = _cursor.getColumnIndexOrThrow("nome");
      final int _cursorIndexOfPreco = _cursor.getColumnIndexOrThrow("preco");
      final int _cursorIndexOfQuantidade = _cursor.getColumnIndexOrThrow("quantidade");
      final List<Produto> _result = new ArrayList<Produto>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Produto _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final BigDecimal _tmpPreco;
        final Double _tmp;
        if (_cursor.isNull(_cursorIndexOfPreco)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getDouble(_cursorIndexOfPreco);
        }
        _tmpPreco = __bigDecimalConverter.paraBigDecimal(_tmp);
        final int _tmpQuantidade;
        _tmpQuantidade = _cursor.getInt(_cursorIndexOfQuantidade);
        _item = new Produto(_tmpId,_tmpNome,_tmpPreco,_tmpQuantidade);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Produto buscaProduto(long id) {
    final String _sql = "SELECT * FROM Produto WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfNome = _cursor.getColumnIndexOrThrow("nome");
      final int _cursorIndexOfPreco = _cursor.getColumnIndexOrThrow("preco");
      final int _cursorIndexOfQuantidade = _cursor.getColumnIndexOrThrow("quantidade");
      final Produto _result;
      if(_cursor.moveToFirst()) {
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final BigDecimal _tmpPreco;
        final Double _tmp;
        if (_cursor.isNull(_cursorIndexOfPreco)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getDouble(_cursorIndexOfPreco);
        }
        _tmpPreco = __bigDecimalConverter.paraBigDecimal(_tmp);
        final int _tmpQuantidade;
        _tmpQuantidade = _cursor.getInt(_cursorIndexOfQuantidade);
        _result = new Produto(_tmpId,_tmpNome,_tmpPreco,_tmpQuantidade);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
