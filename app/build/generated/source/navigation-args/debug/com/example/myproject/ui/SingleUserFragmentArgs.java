package com.example.myproject.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class SingleUserFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private SingleUserFragmentArgs() {
  }

  private SingleUserFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static SingleUserFragmentArgs fromBundle(@NonNull Bundle bundle) {
    SingleUserFragmentArgs __result = new SingleUserFragmentArgs();
    bundle.setClassLoader(SingleUserFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("id")) {
      int id;
      id = bundle.getInt("id");
      __result.arguments.put("id", id);
    } else {
      __result.arguments.put("id", -1);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  public int getId() {
    return (int) arguments.get("id");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("id")) {
      int id = (int) arguments.get("id");
      __result.putInt("id", id);
    } else {
      __result.putInt("id", -1);
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    SingleUserFragmentArgs that = (SingleUserFragmentArgs) object;
    if (arguments.containsKey("id") != that.arguments.containsKey("id")) {
      return false;
    }
    if (getId() != that.getId()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + getId();
    return result;
  }

  @Override
  public String toString() {
    return "SingleUserFragmentArgs{"
        + "id=" + getId()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(SingleUserFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder() {
    }

    @NonNull
    public SingleUserFragmentArgs build() {
      SingleUserFragmentArgs result = new SingleUserFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setId(int id) {
      this.arguments.put("id", id);
      return this;
    }

    @SuppressWarnings("unchecked")
    public int getId() {
      return (int) arguments.get("id");
    }
  }
}
