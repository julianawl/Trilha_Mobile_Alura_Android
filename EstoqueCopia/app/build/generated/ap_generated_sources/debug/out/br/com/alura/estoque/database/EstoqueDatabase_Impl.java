package br.com.alura.estoque.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import br.com.alura.estoque.database.dao.ProdutoDAO;
import br.com.alura.estoque.database.dao.ProdutoDAO_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class EstoqueDatabase_Impl extends EstoqueDatabase {
  private volatile ProdutoDAO _produtoDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Produto` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `preco` REAL, `quantidade` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"775dfe7d85d7e43cae1adbf9f40e1738\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Produto`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsProduto = new HashMap<String, TableInfo.Column>(4);
        _columnsProduto.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsProduto.put("nome", new TableInfo.Column("nome", "TEXT", false, 0));
        _columnsProduto.put("preco", new TableInfo.Column("preco", "REAL", false, 0));
        _columnsProduto.put("quantidade", new TableInfo.Column("quantidade", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProduto = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProduto = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProduto = new TableInfo("Produto", _columnsProduto, _foreignKeysProduto, _indicesProduto);
        final TableInfo _existingProduto = TableInfo.read(_db, "Produto");
        if (! _infoProduto.equals(_existingProduto)) {
          throw new IllegalStateException("Migration didn't properly handle Produto(br.com.alura.estoque.model.Produto).\n"
                  + " Expected:\n" + _infoProduto + "\n"
                  + " Found:\n" + _existingProduto);
        }
      }
    }, "775dfe7d85d7e43cae1adbf9f40e1738", "11cea8c4ea1cc5a058b51912955fd2cc");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Produto");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Produto`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public ProdutoDAO getProdutoDAO() {
    if (_produtoDAO != null) {
      return _produtoDAO;
    } else {
      synchronized(this) {
        if(_produtoDAO == null) {
          _produtoDAO = new ProdutoDAO_Impl(this);
        }
        return _produtoDAO;
      }
    }
  }
}
