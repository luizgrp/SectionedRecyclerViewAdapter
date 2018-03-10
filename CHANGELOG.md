# Changelog

## 1.2.0 - 10/03/2018

### Section class
- Remove all deprecated constructors.
- Add methods `isItemViewWillBeProvided`, `isHeaderViewWillBeProvided`, `isFooterViewWillBeProvided`, `isLoadingViewWillBeProvided`, `isFailedViewWillBeProvided` and `isEmptyViewWillBeProvided`.
- Add methods `getItemView`, `getHeaderView`, `getFooterView`, `getLoadingView`, `getFailedView` and `getEmptyView`.

### SectionedRecyclerViewAdapter class
- Remove method `getSectionsMap`.
- Add method `getCopyOfSectionsMap`
- Add method `removeSection(Section)`.

### SectionParameters class
- Add factory method for `Builder` class.

### SectionParameters.Builder class
- Deprecate constructor with itemResourceId as parameter.
- Add method `itemResourceId`.
- Add methods `itemViewWillBeProvided`, `headerViewWillBeProvided`, `footerViewWillBeProvided`, `loadingViewWillBeProvided`, `failedViewWillBeProvided` and `emptyViewWillBeProvided`.

### StatelessSection class
- Remove all deprecated constructors.

## 1.1.3 - 22/07/2017

### Section class
- Add checks for resource ids in `setState` and throw exception if missing.

### SectionedRecyclerViewAdapter class
- Add methods `notifyNotLoadedStateChanged`, `notifyStateChangedToLoaded` and `notifyStateChangedFromLoaded`.
- Add methods `notifyHeaderInsertedInSection`, `notifyFooterInsertedInSection`, `notifyHeaderRemovedFromSection` and `notifyFooterRemovedFromSection`.
- Add methods `notifySectionChangedToVisible` and `notifySectionChangedToInvisible`.

## 1.1.2 - 30/06/2017
### SectionParameters class
- Add this class to be used as parameter to constructors of `Section` and `StatelessSection` classes.

### Section class
- Add EMPTY state.
- Deprecate all existing constructors.
- Add constructor with `SectionParameters` as parameter.
- Add LayoutRes annotation to resource id variables and constructor parameters.
- Add `getEmptyResourceId` method.
- Add `getEmptyViewHolder` and `onBindEmptyViewHolder` methods.

### StatelessSection class
- Deprecate all existing constructors.
- Add constructor with `SectionParameters` as parameter.
- Add LayoutRes annotation to resource id variables and constructor parameters.

### SectionedRecyclerViewAdapter class
- Add `getSectionPosition(Section)` method.
- Add `getPositionInAdapter`, `getHeaderPositionInAdapter` and `getFooterPositionInAdapter` methods.
- Add overloaded `notifyItemInsertedInSection`, `notifyItemRangeInsertedInSection`, `notifyItemRemovedFromSection`, `notifyItemRangeRemovedFromSection`, `notifyItemChangedInSection`, `notifyItemRangeChangedInSection`, `notifyItemRangeChangedInSection`, `notifyItemMovedInSection` methods with `Section` as parameter.
- Add `notifyHeaderChangedInSection` and `notifyFooterChangedInSection` methods.

## 1.0.5 - 01/04/2017
### SectionedRecyclerViewAdapter class
- Deprecate `getSectionPosition(int)` method, method `getPositionInSection(int)` created as replacement.
- Add `getSectionPosition(String)` method.
- Add methods `notifyItemInsertedInSection` and `notifyItemRangeInsertedInSection`.
- Add methods `notifyItemRemovedFromSection`, `notifyItemRangeRemovedFromSection`.
- Add methods `notifyItemChangedInSection` and `notifyItemRangeChangedInSection`.
- Add methods `notifyItemRangeChangedInSection` and `notifyItemMovedInSection`.

### AndroidManifest
- Remove `label` from application tag in the manifest file.

## 1.0.4 - 11/05/2016
### SectionedRecyclerViewAdapter class
- Add `getSectionsMap` method.

### AndroidManifest
- Remove `supportsRtl` and `allowBackup` from application tag in the manifest file.

## 1.0.3 - 31/03/2016
### SectionedRecyclerViewAdapter class
- Add `getSectionItemViewType` method.

## 1.0.2 - 30/03/2016
### SectionedRecyclerViewAdapter class
- Change access level to public of methods `getSectionPosition` and `getSectionForPosition`.

## 1.0.1 - 19/02/2016
### SectionedRecyclerViewAdapter class
- Fix issue when setting section to invisible.
